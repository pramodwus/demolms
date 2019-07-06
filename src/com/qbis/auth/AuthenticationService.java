package com.qbis.auth;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.CommonUtil;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.ChangePassword;
import com.qbis.model.HlsPlayer;
import com.qbis.model.HlsurlRequest;
import com.qbis.model.OrderDetails;
import com.qbis.model.Profile;
import com.qbis.model.User;
import com.qbis.model.UserData;
import com.qbis.model.UserProfile;
import com.qbis.services.UserService;

@Component
public class AuthenticationService {

	public void login(AuthRequest request) throws JsonProcessingException {
		String url = "https://devapi.eluminate.in/api/users/login/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(new ObjectMapper().writeValueAsString(request));
		HttpEntity<String> entity = new HttpEntity<String>(new ObjectMapper().writeValueAsString(request), headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println("response " + response);
	}

	public void signup(AuthRequest request) throws JsonProcessingException {
		String url = "https://devapi.eluminate.in/api/users/register/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(new ObjectMapper().writeValueAsString(request));
		HttpEntity<String> entity = new HttpEntity<>(new ObjectMapper().writeValueAsString(request), headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println("response " + response);
	}

	public boolean updateUserProfile(AuthRequest request,String token,String email) throws JsonProcessingException, IOException {
		boolean flag = false;
		try
		{
		String url = "https://qa.eluminate.in/api/user/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization",
		"Token "+token);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(new ObjectMapper().writeValueAsString(request));
		HttpEntity<String> entity = new HttpEntity<>(new ObjectMapper().writeValueAsString(request), headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
		if (response.getBody() != null) {
			String result = response.getBody();
			System.out.println("==================="+result);
			
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_EMAIL_IN_USER, new Object[] { email });
			System.out.println("=========data size"+data.getRows().size());
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					if (row.getRowItem(0) != null) {
						System.err.println("====-----------------------");
					new UserService().updateStudentProfile(request.getUser(),email);
						flag = true;
					}
				}
			}
		}
		System.out.println("response" + response);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;

	}





	public boolean getUserProfile(AuthRequest request) throws JsonProcessingException, IOException {
		boolean flag = false;
		String url = "https://devapi.eluminate.in/api/user/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization",
				"Token eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwiZXhwIjoxNTU2MzY5MzQ4fQ.g3vNEVAMEJBhBf-4Ms8tE2GgxbEqAndu-eMp3sLGqBc");
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(new ObjectMapper().writeValueAsString(request));
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		if (response.getBody() != null) {
			String result = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			UserData user = mapper.readValue(result, UserData.class);
			String email = user.getUser().getEmail();
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_EMAIL_IN_USER, new Object[] { email });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					if (row.getRowItem(0) != null) {
						flag = true;
					}
				}
			}
		}
		return flag;

	}
	/**
	 * This method is used for getting user profile based on token.
	 * 
	 * @param token
	 *            {@link String}
	 * @return {@link String}
	 */
	public UserProfile getAllProfile(String token) {
		try {
			String url = "https://qa.eluminate.in/api/profile/";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Token "+token);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(null, headers);
			ResponseEntity<HashMap<String, UserProfile>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<HashMap<String, UserProfile>>() {
					});
			if(response.getBody() != null)
			{
				UserProfile profile=response.getBody().get("profile");
				return response.getBody().get("profile");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}


	/**
	 * This method is used for getting user profile based on token.
	 * 
	 * @param token
	 *            {@link String}
	 * @return {@link String}
	 */
	public String getUserProfile(String token) {
		try {
			String url = "https://qa.eluminate.in/api/user/";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Token " + token);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(null, headers);
			ResponseEntity<UserData> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<UserData>() {
					});
			System.out.println("response.getStatusCode() " + response.getStatusCode());
			return response.getBody().getUser().getEmail();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Integer getUserProfileUserId(String token) {
		try {
			System.err.println(token);
			String url = "https://qa.eluminate.in/api/user/";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Token " + token);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(null, headers);
			ResponseEntity<UserData> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<UserData>() {
					});
			System.out.println("response.getStatusCode() " + response.getStatusCode());
			System.out.println("response"+response.getBody().getUser().getEmail());
			System.out.println("response"+response.getBody());
			String emailId=response.getBody().getUser().getEmail();
			if(emailId != null && emailId.length()>0)
			{
				User user = new UserService().findOneUser(emailId);
				return user.getUserId();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * This method is used for getting user profile based on token.
	 * 
	 * @param token
	 *            {@link String}
	 * @return {@link String}
	 */
	public OrderDetails getOrderDetails(String token, Long orderId) {
		try {
			String url = "https://qa.eluminate.in/api/eluminate_orders/" + orderId + "/";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Token " + token);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(null, headers);
			ResponseEntity<OrderDetails> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<OrderDetails>() {
					});
			System.out.println("response.getStatusCode() " + response.getStatusCode());
			return response.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	public String getHlsUrl(HlsurlRequest request )
	{
	String hlsUrl=null;
	try {
	System.out.println(new ObjectMapper().writeValueAsString(request));
	String url="https://m29aqf63qa.execute-api.ap-southeast-1.amazonaws.com/dev/";

	RestTemplate template=new RestTemplate();
	HttpHeaders headers=new HttpHeaders();

	headers.add("x-api-key","jTFYYVxPkV3kZ0NExmPVs4qscHBKPRN5a2XY8u7h");
	headers.setContentType(MediaType.APPLICATION_JSON);
	String requestUpperCase=new ObjectMapper().writeValueAsString(request).toUpperCase();
	System.out.println(headers.containsKey("x-api-key"));
	HttpEntity<String> entity = new HttpEntity<String>(requestUpperCase,headers);

	ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, String.class);
	System.out.println("response " + response);
	hlsUrl=response.getBody(); 
	}
	catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}

	return hlsUrl;
	}
	
	public HlsPlayer getCookieDataForPlayer(HttpServletResponse response,String token)
	{
System.out.println("Indide getCookieDataForPlayer");
		HlsurlRequest request=new HlsurlRequest();
		String url="https://qa.eluminate.in/api/videoplayer/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.add("Authorization", "Token " + token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		ResponseEntity<HlsurlRequest> responseEntity=restTemplate.exchange(url,HttpMethod.GET, entity, HlsurlRequest.class);
		request.setCloudFrontPolicy(responseEntity.getBody().getCloudFrontPolicy());
		request.setCloudFrontSignature(responseEntity.getBody().getCloudFrontSignature());
		request.setCloudFrontKeyPairId(responseEntity.getBody().getCloudFrontKeyPairId());
		System.out.println("CloudFrontPolicy"+request.getCloudFrontPolicy()+"CloudFrontSignature"+request.getCloudFrontSignature()+"CloudFrontKeyPairId"+request.getCloudFrontKeyPairId());
		
		CommonUtil.setHlsPLayerCookie(request,response);
		return null;
		
	}
	
	
	
	
	
	
	public boolean changePassword(String token ,ChangePassword request) {
		boolean flag=false;
		ResponseEntity<String> response=null;
		try {
		
		String url = "https://qa.eluminate.in/api/password_reset/reset_password/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		System.out.println(new ObjectMapper().writeValueAsString(request)+token);
		headers.add("Authorization", "Token " + token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(new ObjectMapper().writeValueAsString(request), headers);
		response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		if (response.getBody() != null) {
			flag=true;
			System.out.println(response);
			System.out.println("----------"+response.getBody());
				/*
				 * String result = response.getBody(); ObjectMapper mapper = new ObjectMapper();
				 * UserData user = mapper.readValue(result, UserData.class); String email =
				 * user.getUser().getEmail(); QueryData data =
				 * QueryManager.execuateQuery(QueryStrings.CHECK_EMAIL_IN_USER, new Object[] {
				 * email }); if (data.getRows().size() > 0) { for (QueryData.Row row :
				 * data.getRows()) r{ if (row.getRowItem(0) != null) { flag = true; } } }
				 */
		}

		System.out.println(response);
		}
		catch(Exception e) {
      e.printStackTrace();
		}
		return flag;
		}

	public Profile getUserShortProfile(String token)
	{
	Profile profile = new Profile();
	try {
	System.out.println("shortprofile mein aaya "+token);
	String url="https://qa.eluminate.in/api/shortprofile/";
	RestTemplate restTemplate= new RestTemplate();
	HttpHeaders headers=new HttpHeaders();
	headers.add("Authorization ","token "+token);
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> entity =new HttpEntity<String>(headers);


	ResponseEntity<HashMap<String, Profile>> responseEntity = restTemplate.exchange(url,HttpMethod.GET,entity,
	new ParameterizedTypeReference<HashMap<String, Profile>>() {
	});
	profile=responseEntity.getBody().get("profile");


	}
	catch (Exception e) {
	// TODO: handle exception
	}
	return profile;
	}

	
	public String uploadProfilePicture(String token,Integer userId,MultipartFile file)
	{
	String image=null;
	try{
	String url="https://qa.eluminate.in/api/profile/image/"+userId+"/";
	System.err.println(url);

			
			RestTemplate restTemplate = new RestTemplate();
	
		
     		HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Token " + token);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		    MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("image", file);
	
	
			HttpEntity<Object> entity = new HttpEntity<>(map, headers);
			System.err.println("fiel image name====="+file);
			ResponseEntity<HashMap<String, String>> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity,new ParameterizedTypeReference<HashMap<String, String>>() {
			});
			// Force the request expires
			
			System.out.println(responseEntity);
			System.err.println("image url===================="+responseEntity.getBody());
			System.err.println(responseEntity.getBody().get("image"));
		    if(responseEntity.getBody() != null)
		    {
		    image=responseEntity.getBody().get("image");
		    }
			System.out.println("-------------------------image"+image); 
	}
	catch (Exception e) {
	// TODO: handle exception
		e.printStackTrace();
	}
	return image;

	}
	
	
	
	
}


