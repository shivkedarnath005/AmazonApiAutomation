package com.amazon.testcases;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class LoginTest 
{
	private static String token;
	
	@Test
    public void loginTest() 
	{
		
		RestAssured.baseURI = "https://www.amazon.com/";
		
		Map<String,Object> loginDetails = new HashMap<>();
		loginDetails.put("username", "amoldeokar65");
		loginDetails.put("password", "amol123");
		
        // Logging in with username and password
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(loginDetails)
                .post("/auth/login");

        // Extract token and assert login success
        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token should not be null");
        System.out.println("Login successful. Token: " + token);
    }
}
