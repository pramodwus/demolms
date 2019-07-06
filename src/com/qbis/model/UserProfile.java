package com.qbis.model;

public class UserProfile {
	private String email;
    private String first_name;
    private String last_name;
    private String address;
    private String image;
    private String contact_info;
    private Integer user_group;
    private Boolean contact_verified;
    private Integer cart_id;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getContact_info() {
		return contact_info;
	}
	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}
	public Integer getUser_group() {
		return user_group;
	}
	public void setUser_group(Integer user_group) {
		this.user_group = user_group;
	}
	public Boolean getContact_verified() {
		return contact_verified;
	}
	public void setContact_verified(Boolean contact_verified) {
		this.contact_verified = contact_verified;
	}
	public Integer getCart_id() {
		return cart_id;
	}
	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}
    

}



