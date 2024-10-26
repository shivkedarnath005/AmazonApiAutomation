package com.amazon.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class CartCheckout 
{
	private static String baseUrl;
	private static String token;
	private static String orderId;
	
	@Test
	public void checkoutTest() 
	{
		baseUrl = "www.amazon.com";
		String checkoutEndpoint = "/cart/checkout";
		
		Response response = RestAssured
				.given()
				.header("Authorization", "Bearer " + token)
				.when()
				.post(baseUrl + checkoutEndpoint);
		
		response.then().statusCode(200);
		orderId = response.jsonPath().getString("orderId");
		Assert.assertNotNull(orderId, "Order ID is Null");
			
	}
}
