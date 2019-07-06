package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HlsurlRequest{

private String URL;
private Integer EXPIRE;

@JsonProperty("CloudFront-Policy")
private String cloudFrontPolicy;

@JsonProperty("CloudFront-Signature")
private String cloudFrontSignature;

@JsonProperty("CloudFront-Key-Pair-Id")
private String cloudFrontKeyPairId;




public String getURL() {
return URL;
}
public void setURL(String URL) {
this.URL = URL;
}
public Integer getEXPIRE() {
return EXPIRE;
}
public void setEXPIRE(Integer EXPIRE) {
this.EXPIRE = EXPIRE;
}
public String getCloudFrontPolicy() {
	return cloudFrontPolicy;
}
public void setCloudFrontPolicy(String cloudFrontPolicy) {
	this.cloudFrontPolicy = cloudFrontPolicy;
}
public String getCloudFrontSignature() {
	return cloudFrontSignature;
}
public void setCloudFrontSignature(String cloudFrontSignature) {
	this.cloudFrontSignature = cloudFrontSignature;
}
public String getCloudFrontKeyPairId() {
	return cloudFrontKeyPairId;
}
public void setCloudFrontKeyPairId(String cloudFrontKeyPairId) {
	this.cloudFrontKeyPairId = cloudFrontKeyPairId;
}


}