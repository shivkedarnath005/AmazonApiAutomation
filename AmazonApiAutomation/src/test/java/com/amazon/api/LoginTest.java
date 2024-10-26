package com.amazon.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class LoginTest 
{
	private static String baseUrl;
	private static String token;
	
	@Test
	public void loginTest() 
	{
		baseUrl = "https://www.amazon.com";
		String loginEndpoint = "/auth/login";
		
		Map<String,Object> loginDetails = new HashMap<>();
		loginDetails.put("email", "johndoe12@gmail.com");
		loginDetails.put("password", "john123");
		
		Response response = RestAssured
				.given()
				.contentType("application/json")
				.body(loginDetails)
				.when()
				.post(baseUrl + loginEndpoint);
		
		response.then().statusCode(200);
		token = response.jsonPath().getString("token");
		Assert.assertNotNull(token, "Token is Null");
		
	}
}
