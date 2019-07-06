package com.qbis.model;


/**
* 
* @author Shivansh Pandey
*
*/

public class HlsPlayer {
	
	private String CloudFrontPolicy;
	private String CloudFrontSignature;
	private String CloudFrontKeyPairId;
	
	
	public String getCloudFrontPolicy() {
		return CloudFrontPolicy;
	}
	public void setCloudFrontPolicy(String cloudFrontPolicy) {
		CloudFrontPolicy = cloudFrontPolicy;
	}
	public String getCloudFrontSignature() {
		return CloudFrontSignature;
	}
	public void setCloudFrontSignature(String cloudFrontSignature) {
		CloudFrontSignature = cloudFrontSignature;
	}
	public String getCloudFrontKeyPairId() {
		return CloudFrontKeyPairId;
	}
	public void setCloudFrontKeyPairId(String cloudFrontKeyPairId) {
		CloudFrontKeyPairId = cloudFrontKeyPairId;
	}
	

}
