package com.amazon.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class SearchProduct 
{
	private static String baseUrl;
	private static String productId;
	
	@Test
	public void searchProductTest() 
	{
		baseUrl = "www.amazon.com";
		String searchEndpoint = "/products?search";
		
		Response response = RestAssured
				.given()
				.queryParam("search", "T-Shirts")
				.when()
				.get(baseUrl + searchEndpoint);
		
		response.then().statusCode(200);
		List<Map<String,Object>> products = response.jsonPath().getList("products");
		Assert.assertTrue(products.size()>0);
		
		productId = products.get(0).get("id").toString();
		System.out.println("Product Found : " + productId);
		
	}
}
