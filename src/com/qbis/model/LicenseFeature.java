package com.qbis.model;

import java.util.List;

public class LicenseFeature {
	
	private Integer featureId;
	
	private String featureName;
	
	//private List<LicenseSubFeature> subFeatureList;
	
	private List<LicenseSubDetails> licenseSubDetails;

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

/*	public List<LicenseSubFeature> getSubFeatureList() {
		return subFeatureList;
	}

	public void setSubFeatureList(List<LicenseSubFeature> subFeatureList) {
		this.subFeatureList = subFeatureList;
	}*/
	public List<LicenseSubDetails> getLicenseSubDetails() {
		return licenseSubDetails;
	}

	public void setLicenseSubDetails(List<LicenseSubDetails> licenseSubDetails) {
		this.licenseSubDetails = licenseSubDetails;
	}
}
