package com.amazon.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class AddToCart 
{
	private static String baseUrl;
	private static String token;
	private static String productId;
	
	@Test
	public void addToCartTest() 
	{
		baseUrl = "www.amazon.com";
		String cartEndpoint = "/cart/add";
		
		Map<String,Object> cartItems =new HashMap<>();
		cartItems.put("product_Id", productId);
		cartItems.put("quantity", 5);
		
		Response response = RestAssured
				.given()
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.body(cartItems)
				.when()
				.post(baseUrl + cartEndpoint);
		
		response.then().statusCode(201);
		
		
	}
}
