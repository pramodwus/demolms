package com.qbis.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.Config;
import com.qbis.model.ConfigMapping;
import com.qbis.model.Section;
import com.qbis.model.Session;

@Service
public class ConfigService {

	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private static final Logger logger = Logger.getLogger(ConfigService.class);

	/**
	 * This method is used for getting tags.
	 * 
	 * @param object
	 *            {@link Object[]}
	 * @return {@link List}
	 */
	public List<Config> getConfigTags(Object[] object) {
		logger.debug("Inside ConfigService in getTagsForAssessment method:::::: ");
		List<Config> configList = new ArrayList<Config>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_CONFIG_LIST_BASED_ON_KEYS, object);
			for (QueryData.Row row : data.getRows()) {
				Config config = new Config();
				config.setId(Integer.parseInt(row.getRowItem(0)));
				config.setKey(row.getRowItem(1));
				config.setName(row.getRowItem(2));
				config.setType(row.getRowItem(3));
				config.setConfigList(getConfigMapping(config.getId()));
				configList.add(config);
			}
		} catch (Exception ex) {
			logger.debug("Exception Inside ConfigService in getTagsForAssessment method:::::: ", ex);
		}
		return configList;
	}
	
	
	
	public Integer getCourseId(String courseName)
	{
		List<Section> sections=new ArrayList<Section>();
		Integer courseId=null;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_ID,new Object[] {courseName+"%"});
			for (QueryData.Row row : data.getRows()) {
				courseId=Integer.parseInt(row.getRowItem(0));
				System.out.println("course_name "+courseId);
		}
		}
			catch (Exception e) {
				// TODO: handle exception
			}
			return courseId;
	}
	
	
	
	public List<com.qbis.model.Section> getSectionNames(Integer courseId)
	{
		List<Section> sections=new ArrayList<Section>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SECTION_NAME_FOR_TEST_TAGS,new Object[] {courseId});
			for (QueryData.Row row : data.getRows()) {
				Section section=new Section();
				section.setSectionId(Integer.parseInt(row.getRowItem(0)));
				section.setSectionName(row.getRowItem(1));
				System.out.println("sectionName "+section.getSectionName());
			sections.add(section);
		}
		}
			catch (Exception e) {
				// TODO: handle exception
			}
			return sections;
	}
	

	public List<Session> getSessionNames(Integer sectionId)
	{
		List<Session> sessions=new ArrayList<Session>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SESSION_NAME_FOR_TEST_TAGS,new Object[] {sectionId});
			for (QueryData.Row row : data.getRows()) {
				Session session=new Session();
				session.setSessonId(Integer.parseInt(row.getRowItem(0)));
				session.setSessionName(row.getRowItem(1));
				System.out.println("sessionName "+session.getSessionName());
			sessions.add(session);
		}
		}
			catch (Exception e) {
				// TODO: handle exceptionSystem.out.println("sectionName "+section.getSectionName());
			}
			return sessions;
	}
	
	
	

	/**
	 * This method is used for getting config mapping.
	 * 
	 * @param id
	 *            {@link Integer}
	 * @return {@link List}
	 */
	public List<ConfigMapping> getConfigMapping(Integer id) {
		logger.debug("Inside ConfigService in getConfigMapping method:::::: ");
		List<ConfigMapping> configList = new ArrayList<ConfigMapping>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_CONFIG_MAPPING_LIST_BASED_ON_CONFIG_ID,
				new Object[] { id });
		for (QueryData.Row row : data.getRows()) {
			ConfigMapping mapping = new ConfigMapping();
			mapping.setId(Integer.parseInt(row.getRowItem(0)));
			mapping.setConfigId(Integer.parseInt(row.getRowItem(1)));
			mapping.setValue(row.getRowItem(2));
			mapping.setOrder(Integer.parseInt(row.getRowItem(3)));
			configList.add(mapping);
		}
		return configList;
	}
}
