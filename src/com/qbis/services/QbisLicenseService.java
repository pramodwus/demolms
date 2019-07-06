package com.qbis.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.qbis.common.CommonUtil;
import com.qbis.common.LicenseEnum;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.License;
import com.qbis.model.LicenseFeature;
import com.qbis.model.LicenseSubDetails;
import com.qbis.model.Question;

/**
 * This class is used for performing all operations related to details of
 * license service.
 * 
 * @author Neeraj Singh
 *
 */
@Component
public class QbisLicenseService {

	/**
	 * Method to get details of license of a organization.
	 * 
	 * @param orgId
	 * @return license object.
	 */
	public static License companyLicenseDetails(int orgId){
		License license = null;
		try {			
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_COMPANY_LICENSE_DATA_BY_COMPANYID, new Object[] {orgId });
			for (QueryData.Row row : data.getRows()) {
				license = new License();
				license.setLicenseId(Integer.parseInt(row.getRowItem(0)));
				license.setLicenseName(row.getRowItem(1));
				license.setValidityDays(Integer.parseInt(row.getRowItem(2)));
				license.setCost(Double.parseDouble(row.getRowItem(3)));
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				Date date = formatter.parse(row.getRowItem(6));
				license.setBuyDate(date);
				license.setPaymentStatus(Integer.parseInt(row.getRowItem(7)));
		// get feature data by license id
				LicenseFeature licensefeature = null;
				List<LicenseFeature> licenseFeatureList = new ArrayList<LicenseFeature>();
				QueryData dataFeature = QueryManager.execuateQuery(
						QueryStrings.GET_LICENSE_FEATURE_BY_LICENSEID, new Object[] {license.getLicenseId()});
				for (QueryData.Row rowFeature : dataFeature.getRows()) {
					licensefeature = new LicenseFeature();
					licensefeature.setFeatureId(Integer.parseInt(rowFeature.getRowItem(0)));
					licensefeature.setFeatureName(rowFeature.getRowItem(1));
		          /*
		           * get Sub feature data by license id and featureId
		           */
					QueryData dataSub = QueryManager.execuateQuery(
							QueryStrings.GET_LICENSE_SUBDATA_BY_LICENSEID_FEATUREID, new Object[]{
							license.getLicenseId(),licensefeature.getFeatureId()});
	                List<LicenseSubDetails> licenseSubDetails = new ArrayList<LicenseSubDetails>(); 
					LicenseSubDetails subDetails = null;
					for (QueryData.Row rowSub : dataSub.getRows()) {
						subDetails = new LicenseSubDetails();
						subDetails.setSubFeatureName(rowSub.getRowItem(1));
						subDetails.setMaxCount(rowSub.getRowItem(2));
						licenseSubDetails.add(subDetails);
					}
					licensefeature.setLicenseSubDetails(licenseSubDetails);
					licenseFeatureList.add(licensefeature);
				}
				
				license.setLicenseFeatureList(licenseFeatureList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return license;
	}
	
	/**
	 * Method to check organization/company exist or not.
	 * 
	 * @param orgId
	 * @return boolean true if exist else false.
	 */
	public static boolean checkOrgnazationIdExistence(int orgId){
		boolean flag = false;
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.FIND_COMPANY_BY_ID, new Object[] {orgId});
		if(data.getRows().size()>0){
			flag = true;
		}		
		return flag;
	}
	
	/**
	 * Method to check validity license of organization/company expire or not.
	 * 
	 * @param orgId
	 * @return boolean true if not expire else false.
	 */
	public static Boolean checkLicenseValidity(int orgId){
		Boolean flag = false;
		Integer k = null;
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.CHECK_VALIDITY_DETAILS, new Object[] {orgId});
		if(data.getRows().size()>0){
			for (QueryData.Row row : data.getRows()) {
				k = Integer.parseInt(row.getRowItem(0));
			}
			flag = (k==0)?true:false;
		}else{
			flag = null;
		}
		
		return flag;
	}
	
	
	/**
	 * Method to validate license data from different services of qbis.
	 * 
	 * @param License, object contains organization license details
	 * @param p_orgId
	 * @param p_case, service name in which validation is performed 
	 * @return boolean
	 */
	public  License validateLicense(License p_licenseobj,int p_orgId,String p_case){
		License license = new License();
		boolean flag = false;		
		switch (p_case) {
		case "MAXALLOWEDTEST":
			flag = testCreateValidate(p_licenseobj,p_orgId,p_case);
			license.setValid(flag);
			break;
		case "MAXALLOWEDCOURSE":
			flag = courseCreateValidate(p_licenseobj,p_orgId,p_case);
			license.setValid(flag);
			break;
		case "QUESTION_TYPE":
			license = getFilterQuestionType(p_licenseobj,p_orgId,p_case);
			break;
		case "COURSECONTENTSPACE":
			license = validateCourseContentSpace(p_licenseobj,p_orgId,p_case);			
			break;
		case "MAXALLOWEDUSER":
			license = userCreateValidate(p_licenseobj,p_orgId,p_case);			
			break;	

		default:
			break;
		}
		
		return license;
	}

	/**
	 * Method to validate course content size/space of a organization.	  
	 * @param orgId
	 * @param p_licenseobj contains license data
	 * @param p_case contains service name
	 * @return License Object contains status,
	 * true if size is available
	 * false if not available and also the remaining space
	 */	
	private License validateCourseContentSpace(License p_licenseobj, int p_orgId, String p_case) {
		boolean flag = false;
		License license = new License();
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_CONTENT_USED_SPACE_BY_USERID, new Object[] {p_licenseobj.getUserId()});
		Long available_space = 0L;
		Long used_space = 0L;
		for (QueryData.Row row : data.getRows()) {
			used_space = Long.parseLong(row.getRowItem(0));			
		}
		license.setUsedSpace(used_space);
		if(p_licenseobj.getLicenseFeatureList()!=null)for (int i = 0; i < p_licenseobj.getLicenseFeatureList().size(); i++) {
			if(p_licenseobj.getLicenseFeatureList().get(i).getFeatureName().equalsIgnoreCase(LicenseEnum.SPACE.getValue())){
				List<LicenseSubDetails> list = p_licenseobj.getLicenseFeatureList().get(i).getLicenseSubDetails();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getSubFeatureName().equalsIgnoreCase(p_case)){
						available_space = Long.parseLong(list.get(j).getMaxCount());
						break;
					}				
				}
			}			
		}
		license.setAvailableSpace(CommonUtil.convertBytesToGB(available_space));
		Long remainingSpace = available_space - used_space;
		used_space = used_space+p_licenseobj.getFileSize();		
		if(available_space>used_space){
			flag = true;
		}else{
			flag = false;
		}
		license.setValid(flag);		
		long fileSizeInKB = remainingSpace / 1024;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		long fileSizeInMB = fileSizeInKB / 1024;
		license.setFileSize(fileSizeInMB);
		return license;
	}

	/**
	 * Method to get question type of a organization.
	 * 
	 * @param orgId
	 * @param p_licenseobj contains license data
	 * @param p_case contains service name
	 * @return License object having question type list
	 */	
	private License getFilterQuestionType(License p_licenseobj, int p_orgId, String p_case) {
		License license = new License();
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_ALL_ACTIVE_UESTION_TYPES, new Object[] {});
		List<Question> listque = new ArrayList<Question>();
		
		for (QueryData.Row row : data.getRows()) {
			Question que = new Question();
			que.setQuestionType(Integer.parseInt(row.getRowItem(0)));
			que.setQuestionTypeName(row.getRowItem(1));
			listque.add(que);
		}
		
		List<LicenseSubDetails> listsub = new ArrayList<LicenseSubDetails>();
		for (int i = 0; i < p_licenseobj.getLicenseFeatureList().size(); i++) {
			if(p_licenseobj.getLicenseFeatureList().get(i).getFeatureName().equalsIgnoreCase(p_case)){
				 listsub = p_licenseobj.getLicenseFeatureList().get(i).getLicenseSubDetails();
				 break;
			}			
		}
		
		List<Question> tempList = new ArrayList<Question>();
		for (int i = 0; i < listque.size(); i++) {
			for (int j = 0; j < listsub.size(); j++) {				
				if(listque.get(i).getQuestionTypeName().equalsIgnoreCase(listsub.get(j).getSubFeatureName())){
					Question question = new Question();					
					question.setQuestionType(listque.get(i).getQuestionType());
					question.setQuestionTypeName(listque.get(i).getQuestionTypeName());
					tempList.add(question);
					break;
				}
			}
		}
		license.setListQuesType(tempList);		
		return license;
	}

	/**
	 * Method to validity license that maximum test creation by organization.
	 * 
	 * @param orgId
	 * @param p_licenseobj contains license data
	 * @param p_case contains service name
	 * @return boolean,
	 * true organization can create new test,
	 * false organization can not create new test  
	 */	
	public Boolean testCreateValidate(License p_licenseobj,int orgId,String p_case){
		Integer maxAllowed = null;
		Integer orgTestCount = null;
		Boolean flag = null;
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_TEST_COUNT_BY_ORGID, new Object[] {orgId});
		for (QueryData.Row row : data.getRows()) {			
			orgTestCount = Integer.parseInt(row.getRowItem(0));
		}
				
		for (int i = 0; i < p_licenseobj.getLicenseFeatureList().size(); i++) {
			if(p_licenseobj.getLicenseFeatureList().get(i).getFeatureName().equalsIgnoreCase(LicenseEnum.TEST.getValue())){
				List<LicenseSubDetails> list = p_licenseobj.getLicenseFeatureList().get(i).getLicenseSubDetails();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getSubFeatureName().equalsIgnoreCase(p_case)){
						maxAllowed = Integer.parseInt(list.get(j).getMaxCount());
						break;
					}				
				}
			}			
		}		
		
		if(orgTestCount<maxAllowed){
			flag = true;
		}else{
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * Method to validity license that maximum course creation by organization.
	 * 
	 * @param orgId
	 * @param p_licenseobj contains license data
	 * @param p_case contains service name
	 * @return boolean,
	 * true organization can create new course,
	 * false organization can not create new course  
	 */	
	public Boolean courseCreateValidate(License p_licenseobj,int orgId,String p_case){
		Integer maxAllowed = null;
		Integer orgCourseCount = null;
		Boolean flag = null;
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_COURSE_COUNT_BY_ORGID, new Object[] {orgId});
		for (QueryData.Row row : data.getRows()) {			
			orgCourseCount = Integer.parseInt(row.getRowItem(0));
		}
		
		for (int i = 0; i < p_licenseobj.getLicenseFeatureList().size(); i++) {
			if(p_licenseobj.getLicenseFeatureList().get(i).getFeatureName().equalsIgnoreCase(LicenseEnum.COURSE.getValue())){
				List<LicenseSubDetails> list = p_licenseobj.getLicenseFeatureList().get(i).getLicenseSubDetails();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getSubFeatureName().equalsIgnoreCase(p_case)){
						maxAllowed = Integer.parseInt(list.get(j).getMaxCount());
						break;
					}				
				}
			}			
		}
		
		if(orgCourseCount<maxAllowed){
			flag = true;
		}else{
			flag = false;
		}
		
		return flag;
	}
	
	
	/**
	 * Method to validate license that maximum users creation by organization.
	 * 
	 * @param orgId
	 * @param p_licenseobj contains license data
	 * @param p_case contains service name
	 * @return boolean,
	 * true organization can create new user,
	 * false organization can not create new user  
	 */	
	public License userCreateValidate(License p_licenseobj,int orgId,String p_case){
		License license = new License();
		Integer maxAllowed = null;
		Integer orgUserCount = null;
		Boolean flag = null;
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_USER_COUNT_BY_ORGID, new Object[] {orgId});
		for (QueryData.Row row : data.getRows()) {			
			orgUserCount = Integer.parseInt(row.getRowItem(0));
		}
				
		for (int i = 0; i < p_licenseobj.getLicenseFeatureList().size(); i++) {
			if(p_licenseobj.getLicenseFeatureList().get(i).getFeatureName().equalsIgnoreCase(LicenseEnum.USERS.getValue())){
				List<LicenseSubDetails> list = p_licenseobj.getLicenseFeatureList().get(i).getLicenseSubDetails();
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).getSubFeatureName().equalsIgnoreCase(p_case)){
						maxAllowed = Integer.parseInt(list.get(j).getMaxCount());
						break;
					}				
				}
			}			
		}
		
		if(orgUserCount<maxAllowed){
			flag = true;
		}else{
			flag = false;
		}
		license.setValid(flag);
		license.setCount(maxAllowed);
		return license;
	}
    
	/**
	 * This is used getting license list.
	 * @return map as list
	 */
	public Map<Integer,String> getLicenseList() {
		Map<Integer,String> map = new HashMap<Integer,String>();
		try{
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_LICENSE_LIST, new Object[] {});
		License lic = null;
		for (QueryData.Row row : data.getRows()) {
			lic = new License();
			lic.setLicenseId(Integer.parseInt(row.getRowItem(0)));
			lic.setLicenseName(row.getRowItem(1));
			//list.add(lic);
			map.put(Integer.parseInt(row.getRowItem(0)), row.getRowItem(1));
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * This is used getting license list.
	 * @return map as list
	 */
	public License getLicenseDetails(int orgId) {
		QueryData data1 = QueryManager.execuateQuery(
				QueryStrings.GET_LICENCE_VALIDITY_DAYS, new Object[] {orgId});
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_LICENCE_DETAILS_BASED_ON_ORG, new Object[] {data1.getRows().get(0).getRowItem(0),orgId});
		License lic = null;
		for (QueryData.Row row : data.getRows()) {
			lic = new License();
			lic.setLicenseExpiryDate(row.getRowItem(0));
			lic.setLicenseName(row.getRowItem(2));
			lic.setLicenseId(Integer.parseInt(row.getRowItem(3)));
			/*lic.setLicenseId(Integer.parseInt(row.getRowItem(0)));
			lic.setLicenseName(row.getRowItem(1));
			//list.add(lic);
			map.put(Integer.parseInt(row.getRowItem(0)), row.getRowItem(1));*/
		}
		return lic;
	}	
	/**
	 * This is used getting license list.
	 * @return map as list
	 */
	public ArrayList<License> getLicense() {
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_LICENSE_LIST_WITH_PRICE, new Object[] {});
		ArrayList<License> licenseList=new ArrayList<>();
		for (QueryData.Row row : data.getRows()) {
			License lic = new License();
			lic.setLicenseId(Integer.parseInt(row.getRowItem(0)));
			lic.setLicenseName(row.getRowItem(1));
			lic.setValidityDays(Integer.parseInt(row.getRowItem(2)));
			lic.setCost(row.getRowItem(3)!=null?Double.parseDouble(row.getRowItem(3)):null);
			lic=getLicenseSubDetails(lic);
			licenseList.add(lic);
		
	}
		return licenseList;	
}
/**
 * This is used getting license details.
 * Like that total Users,total student,total space etc
 * @param licenseId
 * */
public License getLicenseSubDetails(License license)
{
	try
	{
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_LICENSE_SUB_FEATURES_DETAILS, new Object[] {license.getLicenseId()});
		double monthlyTotalCost=0;
		double yearlyTotalCost=0;
		for (QueryData.Row row : data.getRows())
		{
			
			switch(Integer.parseInt(row.getRowItem(1)))
			{
			case 1:
				   license.setTotalStudent(Integer.parseInt(row.getRowItem(0)));
				   license.setCost(Double.parseDouble(row.getRowItem(2)));
				   license.setYearlyCostPerUser(Double.parseDouble(row.getRowItem(3)));
				   monthlyTotalCost+=Integer.parseInt(row.getRowItem(0))*Integer.parseInt(row.getRowItem(2));
				   yearlyTotalCost+=Integer.parseInt(row.getRowItem(0))*Integer.parseInt(row.getRowItem(3));
				break;
			case 2:
			       license.setTotalTeacher(Integer.parseInt(row.getRowItem(0)));
			       license.setCost(Double.parseDouble(row.getRowItem(2)));
				   license.setYearlyCostPerUser(Double.parseDouble(row.getRowItem(3)));
			       monthlyTotalCost+=Integer.parseInt(row.getRowItem(0))*Integer.parseInt(row.getRowItem(2));
			       yearlyTotalCost+=Integer.parseInt(row.getRowItem(0))*Integer.parseInt(row.getRowItem(3));
				break;	
			case 9: 
				 if(row.getRowItem(0)!=null)license.setAvailableSpace(CommonUtil.convertBytesToGB(Long.parseLong(row.getRowItem(0))));
				break;
			case 10:
				license.setMaxAllowedUser(row.getRowItem(0)!=null?Integer.parseInt(row.getRowItem(0)):0);
			}
			      license.setMonthTotalAmount(monthlyTotalCost);
			      license.setYearlyTotalAmount(yearlyTotalCost*12);/*Yearly cost */
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return license;
	
}
/*
 * Update License Id .when User Pay amount .
 * @Param orgId,licenseId
 * */	
	public void updateLicenseIdInOrg(int orgId,int licenseId)
	{
		try
		{
			QueryManager.execuateUpdate(QueryStrings.UPDATE_LICENSEID_IN_ORG, new Object[] {licenseId,1,orgId});	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void saveTransectionDetails(int orgId,int licenseId,String paymentStatus,String transectionId,String amount)
	{
		try
		{
			QueryManager.execuateQuery(
					QueryStrings.SAVE_TRANSECTION_DEATILS,
					new Object[] {transectionId,paymentStatus,amount,licenseId,orgId});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<String,Object> extractLicenseDetails(License license){
		Map<String,Object> licenseDetails = new HashMap<String,Object>();
		licenseDetails.put("licenseName",license.getLicenseName());
		licenseDetails.put("licenseCost",license.getCost());
		licenseDetails.put("validityDays",license.getValidityDays());
		for(int i=0;i<license.getLicenseFeatureList().size();i++){
			if(license.getLicenseFeatureList().get(i).getFeatureName().equals("SPACE")){
				for(int j=0;j<license.getLicenseFeatureList().get(i).getLicenseSubDetails().size();j++){
					if(license.getLicenseFeatureList().get(i).getLicenseSubDetails().get(j).getSubFeatureName().equals("COURSECONTENTSPACE")){
						String usedSpace =  license.getLicenseFeatureList().get(i).getLicenseSubDetails().get(j).getMaxCount();
						Long usedSpaceBytes = Long.parseLong(usedSpace);
						Long usedSpaceGB = CommonUtil.convertBytesToGB(usedSpaceBytes);
						if(usedSpaceGB>0){
							licenseDetails.put("usedSpace",(usedSpaceGB+" GB"));
						}else{
							licenseDetails.put("usedSpace",(CommonUtil.convertBytesToMB(usedSpaceBytes)+" MB"));
						}
					}
				}
			}
			
			if(license.getLicenseFeatureList().get(i).getFeatureName().equals("USERS")){
				for(int j=0;j<license.getLicenseFeatureList().get(i).getLicenseSubDetails().size();j++){
					if(license.getLicenseFeatureList().get(i).getLicenseSubDetails().get(j).getSubFeatureName().equals("MAXALLOWEDUSER")){
						String maxUsers =  license.getLicenseFeatureList().get(i).getLicenseSubDetails().get(j).getMaxCount();
						licenseDetails.put("maxUsers",maxUsers);
					}
				}
			}
			
		}
		return licenseDetails;
	}
}
