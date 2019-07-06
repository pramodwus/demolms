package com.qbis.model;

import java.util.Date;
import java.util.List;

public class License {
	
	/*
	 * An Integer which describes an unique id of license.
	 */
	private Integer licenseId;
	/*
	 * A String which describes name of license.
	 */
	private String licenseName;
	/*
	 * A String which describes description of license.
	 */
	private String desc;
	/*
	 * A Double which describes cost of license.
	 */
	private Double cost;
	/*
	 * An Integer which describes who create this license.
	 */
	private Integer createdBy;
	/*
	 * An Integer which describes number of days validity of license.
	 */
	private Integer validityDays;
	
	private List<Question> listQuesType;	
	
	/*
	 * Collection to store list of features in license.
	 */
	private List<LicenseFeature> licenseFeatureList;
	
	private Date buyDate;
	
	private int paymentStatus;
	
	/*variable used for internal work*/
	private Boolean valid;
	
	/*variable used for internal work*/
	private Long fileSize;
	
	/*variable used for internal work*/
	private int count;
	
	/*variable used for internal work*/
	private Long usedSpace;
	
	/*variable used for internal work*/
	private Long availableSpace;
	
	/*
	 * An Integer which describes an unique id of user.
	 */
	private Integer userId;
	/*
	 * 
	 * */
	private String licenseExpiryDate;
	private int planType;
	
	private Integer maxAllowedUser;
	
			
	public int getPlanType() {
		return planType;
	}

	public void setPlanType(int planType) {
		this.planType = planType;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	public int getTotalTeacher() {
		return totalTeacher;
	}

	public void setTotalTeacher(int totalTeacher) {
		this.totalTeacher = totalTeacher;
	}

	/*
	 * An Integer used for show total student register in License.
	 */
	private int totalStudent;
	

	/*
	 * An Integer used for show total Teachers register in License.
	 */
	private int totalTeacher;
	/*
	 * An Integer used for show total Cost Based On license Plan.
	 */
	private Double monthTotalAmount;
	/*
	 * An Integer used for show total Cost Based On license Plan.
	 */
	private Double yearlyTotalAmount;
	/*
	 * An Integer used for show Yearly cost Per user.
	 */
    private Double yearlyCostPerUser;
	

	public Double getMonthTotalAmount() {
		return monthTotalAmount;
	}

	public void setMonthTotalAmount(Double monthTotalAmount) {
		this.monthTotalAmount = monthTotalAmount;
	}

	public Double getYearlyTotalAmount() {
		return yearlyTotalAmount;
	}

	public void setYearlyTotalAmount(Double yearlyTotalAmount) {
		this.yearlyTotalAmount = yearlyTotalAmount;
	}

	public Double getYearlyCostPerUser() {
		return yearlyCostPerUser;
	}

	public void setYearlyCostPerUser(Double yearlyCostPerUser) {
		this.yearlyCostPerUser = yearlyCostPerUser;
	}

	public String getLicenseExpiryDate() {
		return licenseExpiryDate;
	}

	public void setLicenseExpiryDate(String licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
	}

	public Integer getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}

	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Integer validityDays) {
		this.validityDays = validityDays;
	}
	
	public List<LicenseFeature> getLicenseFeatureList() {
		return licenseFeatureList;
	}
	
	public void setLicenseFeatureList(List<LicenseFeature> licenseFeatureList) {
		this.licenseFeatureList = licenseFeatureList;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public Boolean getValid() {
		return valid;
	}
	
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	public List<Question> getListQuesType() {
		return listQuesType;
	}
	
	public void setListQuesType(List<Question> listQuesType) {
		this.listQuesType = listQuesType;
	}
	
	public Long getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public Long getUsedSpace() {
		return usedSpace;
	}

	public void setUsedSpace(Long usedSpace) {
		this.usedSpace = usedSpace;
	}

	public Long getAvailableSpace() {
		return availableSpace;
	}

	public void setAvailableSpace(Long availableSpace) {
		this.availableSpace = availableSpace;
	}

	public Integer getMaxAllowedUser() {
		return maxAllowedUser;
	}

	public void setMaxAllowedUser(Integer maxAllowedUser) {
		this.maxAllowedUser = maxAllowedUser;
	}
	

}
