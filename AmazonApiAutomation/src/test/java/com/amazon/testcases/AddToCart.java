package com.amazon.testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.*;
import io.restassured.response.Response;
import junit.framework.Assert;

public class AddToCart 
{
	private static String token;
	private static String productId;
	
	@Test
    public void addToCartTest() 
	{
		
		RestAssured.baseURI = "https://www.amazon.com/";
		
		Map<String,Object> productDetails = new HashMap<>();
		productDetails.put("productId", productId);
		productDetails.put("quantity", 2);
		
		// Add the product to the cart
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(productDetails)
                .post("/cart/add");

        // Assert the product was added successfully
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Product added to cart successfully.");
    }
}
