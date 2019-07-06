package com.qbis.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.auth.AuthenticationService;
import com.qbis.common.ConstantUtil;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.Course;
import com.qbis.model.OrderDetails;
import com.qbis.model.OrderItems;
import com.qbis.model.Product;
import com.qbis.model.User;

/**
 * This service is used fir handling prodcut related implementation.
 * 
 * @author bellurbis
 *
 */
@Service
public class ProductService {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(ProductService.class);

	/**
	 * Instance of {@link AuthenticationService}
	 */
	@Autowired
	private AuthenticationService authService;
	/**
	 * Instance of {@link UserService}
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	/**
	 * This method is used for getting product list.
	 * 
	 * @param offset
	 *            {@link Integer}
	 * @param limit
	 *            {@link Integer}
	 */
	public Map<String, Object> getProductList(Integer offset, Integer limit, String token) {
		logger.debug("Inside ProductService in getProductList method::::::::: offset " + offset + ", limit= " + limit);
		Map<String, Object> result = new HashMap<>();
		List<Product> productList = new ArrayList<Product>();
		List<Product> comboList = new ArrayList<Product>();
		try {
			if (offset == null || offset < 0) {
				offset = 0;
			}
			if (limit == null || limit < 0) {
				limit = ConstantUtil.LIMIT;
			}
			if (limit > ConstantUtil.MAX_LIMIT) {
				limit = ConstantUtil.MAX_LIMIT;
			}
			Integer userId = null;
			if (token != null) {
				String emailId = authService.getUserProfile(token);
				logger.debug("Inside ProductService in getProductList method::::::::: emailId = " + emailId);
				User user = userService.findOneUser(emailId);
				if (user != null && user.getUserId() != null && user.getUserId() > 0) {
					userId = user.getUserId();
				}
			}
			logger.debug("Inside ProductService in getProductList method::::::::: userId = " + userId);
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_PRODUCT_LIST,
					new Object[] { userId, offset, limit });
   			for (QueryData.Row row : data.getRows()) {
				Product product = new Product();
				product.setId(Long.valueOf(row.getRowItem(0)));
				product.setTitle(row.getRowItem(1));
				product.setDescription(row.getRowItem(2));
				product.setPrice(Double.valueOf(row.getRowItem(3)));
				product.setIcon(row.getRowItem(4));
				product.setSubscriber(row.getRowItem(5) != null ? true : false);
				List<Course> courseList = getProductCourseList(product.getId());
				System.out.println("========="+courseList);
				if(product.getId()==4)
				{
					break;
				}
				courseList.forEach(m->{
					System.out.println("==========="+m.getCourseId());
					product.setCourseId(m.getCourseId());
				    product.setCourseName(m.getCourseName());
				    product.setCourseDesc(m.getCourseDesc());
				    product.setCourseTag(m.getCourseTag());
				    product.setPromoVideoUrl(m.getPromoVideoUrl());
				    product.setCourseImageUrl(m.getCourseImageUrl());
				    product.setChaptercount(m.getChaptercount());
				    product.setVideocounts(m.getVideocounts());
				    product.setSessions(m.getSessions());
				});
				product.setCourseList(courseList);
				if (courseList.size() > 1) {
				  String comboName=courseList.stream().map(m->m.getCourseName()).collect(Collectors.joining(" | ","Combo of ",""));
					product.setTitle(comboName);
					product.setVideocounts("16");
					product.setChaptercount("12");
					product.setSessions(1000);
					comboList.add(product);
				} else {
					productList.add(product);
				}
			}
			
			
		} catch (Exception ex) {
			logger.error("Exception Inside ProductService in getProductList method :::::: ", ex);
		}
		result.put("productList", productList);
		result.put("comboList", comboList);
		return result;
	}

	/**
	 * This method is used for getting product by id product.
	 * 
	 * @param productId
	 *            {@link Long}
	 * @return {@link List}
	 */

	public Product getProduct(String token, Integer productId) {
		logger.debug("Inside ProductService in getProduct method:::::::::");
		Product product = null;
		try {

			Integer userId = null;
			if (token != null) {
				String emailId = authService.getUserProfile(token);
				logger.debug("Inside ProductService in getProductList method::::::::: emailId = " + emailId);
				User user = userService.findOneUser(emailId);
				if (user != null && user.getUserId() != null && user.getUserId() > 0) {
					userId = user.getUserId();
				}
			}
			logger.debug("Inside ProductService in getProduct method::::::::: userId = " + userId);
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_PRODUCT_BY_ID,
					new Object[] { userId, productId });
			for (QueryData.Row row : data.getRows()) {
				product = new Product();
				product.setId(Long.valueOf(row.getRowItem(0)));
				product.setTitle(row.getRowItem(1));
				product.setDescription(row.getRowItem(2));
				product.setPrice(Double.valueOf(row.getRowItem(3)));
				product.setSubscriber(row.getRowItem(4) != null ? true : false);
				product.setCourseList(getProductCourseList(product.getId()));
				product.setIcon(
						"https://eluminate-dev.s3.amazonaws.com/static/eluminate/assets/images/home_page1/imageindian_history.png");
			}
		} catch (Exception ex) {
			logger.error("Exception Inside ProductService in getProduct method :::::: ", ex);
		}
		return product;
	}

	
	
		
	private List<Course> getProductCourseList(Long productId) {
		logger.debug("Inside ProductService in getProductCourseList method::::::::: productId= " + productId);
		List<Course> courseList = new ArrayList<Course>();
		QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COUSE_LIST_INSIDE_PRODUCT,
				new Object[] { productId });
		
		  for (QueryData.Row row : data.getRows()) 
		  {
		  Course course = new Course();
		  course.setCourseId(Integer.parseInt(row.getRowItem(0)));
		  course.setCourseName(row.getRowItem(1));
		  course.setCourseDesc(row.getRowItem(2));
		  course.setPromoVideoUrl(row.getRowItem(3) == null || row.getRowItem(3).length() == 0 ? "" :row.getRowItem(3)); 
		  course.setCourseImageUrl(row.getRowItem(4) != null ? ConstantUtil.SERVER_IP + ConstantUtil.ICON_PATH + row.getRowItem(4) : "");
		  course.setSessions(courseService.getSectionCountByCourseId(course.getCourseId())); 
		  if(course.getCourseId()==3)
		  {
			  course.setChaptercount("5");
		  }
		  if(course.getCourseId()==5)
		  {
			  course.setChaptercount("7"); 
		  }
		  //course.setChaptercount(getChapterCount(course.getCourseId()));
		  course.setVideocounts(getVideoCount(course.getCourseId())); String newTag =""; if (row.getRowItem(5) != null) { String tag[] =
		  row.getRowItem(5).replaceAll(" ", "").split(","); for (int i = 0; i < tag.length; i++) { newTag = (i == tag.length - 1 ? (newTag + "#" + tag[i]) : (newTag + "#" + tag[i] + ", "));
		  }
		  } 
		  course.setCourseTag(newTag);
		  courseList.add(course);
		  }
		return courseList;

		}

	
	
	
	/**
	 * This method is used for placing the product order.
	 * 
	 *
	 * @param courseId
	 *            {@link Integer}
	 * @return {@link Integer}
	 */

   
	 public String getChapterCount(Integer courseId)
	 {
		 logger.debug("Inside ProductService in getChapterCount method:::::::::");
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_CHAPTER_COUNT,
					new Object[] { courseId });
			String count=null;
			for (QueryData.Row row : data.getRows()) {
        
            //count=row.getRowItem(0);
				count="8";
			}
		 return count;	 
	 }
	 /**
		 * This method is used for placing the product order.
		 * 
		 * @param token
		 *            {@link String}
		 * @param orderId
		 *            {@link Long}
		 * @return {@link Boolean}
		 */

	 public String getVideoCount(Integer courseId)
	 {
		 String videoCounts=null;
		 logger.debug("Inside ProductService in getVideoCount method:::::::::");
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_COURSE_VIDEO_COUNT,
					new Object[] { courseId });
				for (QueryData.Row row : data.getRows()) {
	        
//	          videoCounts=row.getRowItem(0);
					videoCounts="105";
				}
		 return videoCounts;	 
	 }
	 
	/**
	 * This method is used for placing the product order.
	 * 
	 * @param token
	 *            {@link String}
	 * @param orderId
	 *            {@link Long}
	 * @return {@link Boolean}
	 */
	 public boolean placeProductOrder(String token, Long orderId) {
		 logger.debug("Inside ProductService in placeProductOrder method:::::::::");
		 OrderDetails details = authService.getOrderDetails(token, orderId);
		 String ProductuniqueId =null;
		 String UserUniqueId=null;
		 Boolean isExist=false;
		 Boolean isSaved=false;
		 if (details != null) {
		 try {
		 logger.debug("Inside ProductService in placeProductOrder method::::::::: emailId = "
		 + details.getCustomer().getEmail());
		 User user = userService.findOneUser(details.getCustomer().getEmail());
		 if (user == null || user.getUserId() == null) {
		 return false;
		 }
		 logger.debug(
		 "Inside ProductService in placeProductOrder method::::::::: userId = " + user.getUserId());
		 ObjectMapper mapper = new ObjectMapper();
		 for (int index = 0; index < details.getEluminate_order_items().size(); index++) {
		 OrderItems item = mapper.readValue(details.getEluminate_order_items().get(index),
		 new TypeReference<OrderItems>() {
		 });
		 ProductuniqueId = item.getId().toString();
		 UserUniqueId= user.getUserId().toString();
		 Integer comboUniqueId=Integer.parseInt(ProductuniqueId.concat(UserUniqueId));

		 isExist =getcomboUniqueIdExit(comboUniqueId);
		 if(isExist==false)
		 {
		 QueryManager.execuateUpdate(QueryStrings.SAVE_PRODUCT_SUBSCRIBER,

		 new Object[] { item.getId(), user.getUserId(),comboUniqueId ,details.getTransaction_id() });
		 isSaved=true;
		 }
		 }

		 } catch (Exception ex) {
		 logger.debug("Exception Inside ProductService in placeProductOrder method:::::::::", ex);
		 }
		 }
		 return isSaved;
		 }


		 public boolean getcomboUniqueIdExit(Integer getcomboUniqueIdExit)
		 {
		 Boolean isExist=false;	
		 Integer productId=null;
		 QueryData data = QueryManager.execuateQuery(QueryStrings.GET_UNIQUE_CREDENTIAL,
		 new Object[] { getcomboUniqueIdExit });
		 for (QueryData.Row row : data.getRows()) {
		 productId=Integer.parseInt(row.getRowItem(0));


		 }
		 if(productId!=null)
		 {
		 isExist=true;
		 }
		 return isExist;
		 }
}