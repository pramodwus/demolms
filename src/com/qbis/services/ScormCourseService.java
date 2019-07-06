package com.qbis.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.CourseType;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.Course;
import com.qbis.model.ScormCourseData;
import com.qbis.model.ScormObjective;
import com.qbis.model.ScormObjectives;
import com.qbis.model.ScormOrganizationItem;
import com.qbis.model.ScormPrimaryObjective;
import com.qbis.model.ScormResource;
import com.qbis.model.ScormSequencing;
import com.qbis.model.User;

@Component
public class ScormCourseService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(ScormCourseService.class);
	private static final String IMSMANIFEST_NAME = "imsmanifest.xml";
	private static final Integer default_course_is_paid_value = 0;
	private static final Integer default_course_level_id_value = 1;
	private static final Integer default_course_language_id_value = 1;

	/**
	 * Instance of {@link CourseService}
	 */
	@Autowired
	private CourseService courseService;

	/**
	 * Instance of {@link UserService}
	 */
	@Autowired
	private UserService userService;

	/**
	 * This is used perform operation on scorm zip file like unzip, extract data
	 * etc.
	 * 
	 * @param file
	 * @param user
	 * @return Integer
	 */
	public Integer performOperationOnScormFile(MultipartFile file, User user) {
		logger.log(Level.DEBUG,
				"Inside ScormCourseService in performoperationOnScormFile method.......");
		Integer courseId = 0;
		String uuid = UUID.randomUUID().toString();
		String exactFileName = file.getOriginalFilename().substring(0,
				file.getOriginalFilename().lastIndexOf("."));
		String fileName = uuid + "_" + file.getOriginalFilename();
		String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH
				+ ConstantUtil.SCORM_COURSE_DIRECTORY;
		String source = uploadPath + File.separator + fileName;
		exactFileName = user.getSubdomain() + File.separator + uuid + "_"
				+ exactFileName;
		String destination = uploadPath + File.separator + exactFileName;
		try {
			File uploadfile = new File(source);
			file.transferTo(uploadfile);
			Boolean isUnZip = CommonUtil.unZipTheFile(source, destination);
			String xmlPath = destination + File.separator + IMSMANIFEST_NAME;
			if (isUnZip) {
				File manifestFile = new File(xmlPath);
				if (manifestFile.exists()) {
					if (isValidVersion(xmlPath)) {
						// if (isValidVersion(xmlPath) &&
						// !isMultipleSCO(xmlPath)) {
						String courseName = getScormCourseName(xmlPath);
						if (courseName != null
								&& courseName.trim().length() > 0) {
							uploadfile.delete();
							Course course = new Course();
							course.setCourseName(courseName);
							String highlights[] = {};
							course.setHighlights(highlights);
							course.setSubTitle("");
							course.setCourseTag("Scorm");
							course.setIsPaid(default_course_is_paid_value);
							course.setLevelId(default_course_level_id_value);
							course.setLanguageId(default_course_language_id_value);
							course.setUserId(user.getUserId());
							course.setCourseType(Integer
									.parseInt(CourseType.SCORM_COURSE
											.getValue()));
							course.setScormRootPath(exactFileName);
							Boolean isSaved = courseService
									.saveCourseDetails(course);
							if (isSaved) {
								courseId = course.getCourseId() != null
										&& course.getCourseId() > 0 ? course
										.getCourseId() : 0;
							}
						}
					} else {
						FileUtils.deleteDirectory(new File(destination));
					}
				} else {
					FileUtils.deleteDirectory(new File(destination));
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside ScormCourseService in performoperationOnScormFile method:::::",
					e);
		}
		return courseId;
	}

	/**
	 * This is used for checking that scorm version is valid or not.
	 * 
	 * @param xmlPath
	 * @return Boolean as valid or not.
	 */
	public Boolean isValidVersion(String xmlPath) {
		Boolean isValid = false;
		String scorm_version = null;
		try {
			Node node = readScormCourseManifestXMLFile(xmlPath, "metadata");
			NodeList nodelist = node.getChildNodes();
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node currNode = nodelist.item(i);
				if (currNode.getNodeType() == Node.ELEMENT_NODE) {
					if (currNode.getNodeName().equals("schemaversion")) {
						scorm_version = currNode.getFirstChild()
								.getTextContent();
						break;
					}
				}
			}
			if (scorm_version != null) {
				switch (scorm_version) {
				case "2004 4th Edition":
					isValid = true;
					break;
				case "2004 3rd Edition":
					isValid = true;
					break;
				case "CAM 1.3":
					isValid = true;
					break;
				default:
					logger.log(Level.DEBUG, "Invalid Scorm Version :::: "
							+ scorm_version);
					isValid = false;
					break;
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside ScormCourseService in isValidVersion method:::::",
					e);
			isValid = false;
		}
		return isValid;
	}

	/**
	 * This is used getting course name from reading the xml files.
	 * 
	 * @param xmlPath
	 * @return course name
	 */
	public String getScormCourseName(String xmlPath) {
		String courseName = null;
		Node node = readScormCourseManifestXMLFile(xmlPath, "organization");
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equals("title")) {
					courseName = currentNode.getFirstChild().getTextContent();
					break;
				}
			}
		}
		return courseName;
	}

	/**
	 * This is used getting node from xml based on node's name.
	 * 
	 * @param xmlPath
	 * @param nodeName
	 * @return Node
	 */
	public Node readScormCourseManifestXMLFile(String xmlPath, String nodeName) {
		logger.log(Level.DEBUG,
				"Inside ScormCourseService in readScormCourseManifestXMLFile method.......");
		Document document = null;
		Node cNode = null;
		try {
			File inputFile = new File(xmlPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			document = dBuilder.parse(inputFile);
			NodeList nodeList = document.getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {

				Node currentNode = nodeList.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {

					NodeList nodeList1 = currentNode.getChildNodes();
					for (int j = 0; j < nodeList1.getLength(); j++) {
						Node currentNode1 = nodeList1.item(j);
						if (currentNode1.getNodeType() == Node.ELEMENT_NODE) {

							if (currentNode1.getNodeName().equals(nodeName)) {
								cNode = currentNode1;
								break;
							} else {
								NodeList nodeList11 = currentNode1
										.getChildNodes();
								for (int z = 0; z < nodeList11.getLength(); z++) {
									Node currentNode11 = nodeList11.item(z);
									if (currentNode11.getNodeType() == Node.ELEMENT_NODE) {

										if (currentNode11.getNodeName().equals(
												nodeName)) {
											cNode = currentNode11;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside ScormCourseService in readScormCourseManifestXMLFile method:::::",
					e);
		}
		return cNode;
	}

	/**
	 * This is used for getting scorm course data based on course id.
	 * 
	 * @param courseId
	 * @return root path for manifest.xml file
	 */
	public String getScormCourseInfo(Integer courseId) {
		logger.log(Level.DEBUG,
				"Inside ScormCourseService in getScormCourseInfo method.......");
		String root = null;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_SCORM_COURSE_ROOT_INFO,
					new Object[] { courseId });
			for (QueryData.Row row : data.getRows()) {
				root = row.getRowItem(0);
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside ScormCourseService in getScormCourseInfo method:::::",
					e);
		}
		return root;
	}

	/**
	 * This is used for extracting data from manifest xml file of scorm course.
	 * 
	 * @param userId
	 * @param courseId
	 * @return map
	 */
	public Map<String, Object> extractInfoFromXML(Integer userId,
			Integer courseId) {
		logger.log(Level.DEBUG,
				"Inside ScormCourseService in extractInfoFromXML method.......");
		Map<String, Object> map = new HashMap<String, Object>();
		Integer status = 401;
		try {
			String coursename = getScormCourseInfo(courseId);
			ScormCourseData scormCourseData = new ScormCourseData();
			User user = userService.findOneUser(userId);
			String xmlPath = ConstantUtil.PROFILE_IMAGE_PATH
					+ ConstantUtil.SCORM_COURSE_DIRECTORY + File.separator
					+ coursename + File.separator + IMSMANIFEST_NAME;
			File file = new File(xmlPath);
			scormCourseData.setRootDirName(ConstantUtil.SCORM_COURSE_DIRECTORY);
			scormCourseData.setScormCoursePath(coursename);
			if (file.exists()) {
				/**
				 * if scorm course has multiple sco files.
				 */
				if (isMultipleSCO(xmlPath)) {
					scormCourseData.setIsMultipleSco(true);

				} else {
					scormCourseData.setIsMultipleSco(false);
				}
				setScormCourseData(scormCourseData, xmlPath);
				status = 200;
			}
			map.put("coursedata", scormCourseData);
			map.put("userdata", user);
		} catch (Exception e) {
			status = 401;
			logger.log(
					Level.ERROR,
					"Inside ScormCourseService in extractInfoFromXML method....... ",
					e);
		}
		map.put("status", status);
		return map;
	}

	/**
	 * This is used for extract data from xml file and set them in object.
	 * 
	 * @param scormCourseData
	 * @param xmlPath
	 */
	public void setScormCourseData(ScormCourseData scormCourseData,
			String xmlPath) {
		logger.log(Level.DEBUG,
				"Inside ScormCourseService in setScormCourseData method.......");
		try {
			File inputFile = new File(xmlPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputFile);
			NodeList nodeList = document.getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node currentNode = nodeList.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					ArrayList<ScormOrganizationItem> items = new ArrayList<ScormOrganizationItem>();
					ArrayList<ScormResource> resource = new ArrayList<ScormResource>();
					ScormSequencing sequencing = new ScormSequencing();
					scormCourseData.setItems(items);
					scormCourseData.setResource(resource);
					scormCourseData.setSequencing(sequencing);
					/**
					 * call function for extracting data from a particular node.
					 */
					recursionNode(currentNode, currentNode, scormCourseData);
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside ScormCourseService in setScormCourseData method:::::",
					e);
		}
	}

	/**
	 * This method is used for extract data from a particular node and it will
	 * be called recursively.
	 * 
	 * @param node
	 * @param preNode
	 * @param scormCourseData
	 */
	public void recursionNode(Node node, Node preNode,
			ScormCourseData scormCourseData) {
		NodeList nodeList = node.getChildNodes();
		if (node.getNodeName().equals("title")
				&& preNode.getNodeName().equals("organization")) {
			if (node.getFirstChild().getNodeName() != null
					&& node.getFirstChild().getNodeName().equals("#text")) {
				scormCourseData.setCourseName(node.getFirstChild()
						.getTextContent());
			}
		}
		if (node.getNodeName().equals("item")
				&& preNode.getNodeName().equals("organization")) {
			Element element = (Element) node;
			ScormOrganizationItem item = new ScormOrganizationItem();
			ArrayList<ScormOrganizationItem> itemsArr = new ArrayList<ScormOrganizationItem>();
			item.setItems(itemsArr);
			item.setTitle(element.getElementsByTagName("title").item(0)
					.getTextContent());
			item.setIdentifier(element.getAttribute("identifier"));
			item.setIdentifierref(element.getAttribute("identifierref"));
			item.setParameters(element.getAttribute("parameters"));
			/**
			 * extract sequencing information from this item node.
			 */
			extractSequencingInfoFromItemNode(node, item);
			/**
			 * traverse on item node if it has item type children.
			 */
			recursiveItemNode(node, itemsArr);
			scormCourseData.getItems().add(item);
		}

		if (node.getNodeName().equals("resource")
				&& preNode.getNodeName().equals("resources")) {
			Element element = (Element) node;
			ScormResource resource = new ScormResource();
			resource.setIdentifier(element.getAttribute("identifier"));
			resource.setType(element.getAttribute("type"));
			resource.setScormType(element.getAttribute("adlcp:scormType"));
			resource.setHref(element.getAttribute("href"));

			if (resource.getIdentifier() != null
					&& resource.getScormType() != null
					&& (resource.getScormType().equals("sco") || resource
							.getScormType().equals("asset"))) {
				ScormOrganizationItem item = findScormItemByIdentifier(
						scormCourseData.getItems(), resource.getIdentifier());
				if (item != null && item.getParameters() != null) {
					resource.setHref(resource.getHref() + item.getParameters());
				}
				scormCourseData.getResource().add(resource);
			}
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				recursionNode(currentNode, node, scormCourseData);
			}
		}
	}

	/**
	 * This is used getting scorm item based on identifier.
	 * 
	 * @param items
	 * @param identifier
	 * @return ScormOrganizationItem
	 */
	public ScormOrganizationItem findScormItemByIdentifier(
			ArrayList<ScormOrganizationItem> items, String identifier) {
		ScormOrganizationItem item = null;
		for (int i = 0; i < items.size(); i++) {
			String identifierref = items.get(i).getIdentifierref();
			if (identifierref != null && identifierref.equals(identifier)) {
				item = items.get(i);
				break;
			} else if (items.get(i).getItems() != null) {
				findScormItemByIdentifier(items.get(i).getItems(), identifier);
			}
		}
		return item;
	}

	/**
	 * This is used for checking this scorm course has multiple sco files or not
	 * 
	 * @param xmlPath
	 * @return Boolean
	 */
	public Boolean isMultipleSCO(String xmlPath) {
		Boolean isContain = false;
		Integer totalScoFiles = 0;
		Node node = readScormCourseManifestXMLFile(xmlPath, "resources");
		if (node != null) {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node currentNode = nodeList.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) currentNode;
					String scormType = element.getAttribute("adlcp:scormType");
					String identifier = element.getAttribute("identifier");
					if (scormType != null
							&& (scormType.equals("sco") || scormType
									.equals("asset")) && identifier != null) {
						totalScoFiles++;
					}
				}
			}

			if (totalScoFiles > 1) {
				isContain = true;
			}
		}
		return isContain;
	}

	/**
	 * This function is used for traversing on item node and extract data.
	 * 
	 * @param curr
	 * @param items
	 */
	public void recursiveItemNode(Node currentNode,
			ArrayList<ScormOrganizationItem> items) {
		NodeList list = currentNode.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName().equals("item")) {
					Element element = (Element) node;
					ArrayList<ScormOrganizationItem> itemsArr = new ArrayList<ScormOrganizationItem>();
					ScormOrganizationItem item = new ScormOrganizationItem();
					item.setItems(itemsArr);
					item.setTitle(element.getElementsByTagName("title").item(0)
							.getTextContent());
					item.setIdentifier(element.getAttribute("identifier"));
					item.setIdentifierref(element.getAttribute("identifierref"));
					item.setParameters(element.getAttribute("parameters"));
					/**
					 * extract sequencing information from this item node.
					 */
					extractSequencingInfoFromItemNode(node, item);
					/**
					 * call again for child node.
					 */
					recursiveItemNode(node, itemsArr);
					items.add(item);
				}
			}

		}
	}

	/**
	 * This is used extracting sequencing node information from item node.
	 * 
	 * @param node
	 * @param item
	 */
	public void extractSequencingInfoFromItemNode(Node node,
			ScormOrganizationItem item) {
		NodeList nodelist = node.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node currNode = nodelist.item(i);
			if (currNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currNode.getNodeName().equals("imsss:sequencing")) {
					ScormSequencing sequencing = new ScormSequencing();
					ScormObjectives objectives = new ScormObjectives();
					extractObjectivesListFromSequencingNode(currNode,
							objectives);
					sequencing.setObjectives(objectives);
					item.setSequencing(sequencing);
				}
			}

		}
	}

	/**
	 * This is used extract objectives list from imsss:sequencing node.
	 * 
	 * @param node
	 * @param objectives
	 */
	public void extractObjectivesListFromSequencingNode(Node node,
			ScormObjectives objectives) {
		NodeList nodelist = node.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node currNode = nodelist.item(i);
			if (currNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currNode.getNodeName().equals("imsss:objectives")) {
					ArrayList<ScormObjective> objective = new ArrayList<ScormObjective>();
					ScormPrimaryObjective primaryObjective = new ScormPrimaryObjective();
					objectives.setObjective(objective);
					objectives.setPrimaryObjective(primaryObjective);
					extractObjectiveInfoFromObjectivesList(currNode, objectives);
				}
			}

		}
	}

	/**
	 * This is used extract objective and primary objective information from
	 * imsss:objectives node.
	 * 
	 * @param node
	 * @param objectives
	 */
	public void extractObjectiveInfoFromObjectivesList(Node node,
			ScormObjectives objectives) {
		NodeList nodelist = node.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node currNode = nodelist.item(i);
			if (currNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currNode.getNodeName().equals("imsss:objective")) {
					Element element = (Element) currNode;
					ScormObjective objective = new ScormObjective();
					objective.setObjectiveID(element
							.getAttribute("objectiveID"));
					objectives.getObjective().add(objective);
				} else if (currNode.getNodeName().equals(
						"imsss:primaryObjective")) {
					/**
					 * This will be set only once because only one primary
					 * objective can be in objectives list.
					 */
					Element element = (Element) currNode;
					objectives.getPrimaryObjective().setObjectiveID(
							element.getAttribute("objectiveID"));
				}
			}

		}
	}

}
