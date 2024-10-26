package com.amazon.testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.*;
import io.restassured.response.Response;
import junit.framework.Assert;

public class SearchProduct 
{
	private static String token;
	private static String productId;
	
	@Test
    public void searchProductTest() 
	{
		
		RestAssured.baseURI = "https://www.amazon.com/";
		
		// Searching for iPhone 5 product
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .queryParam("q", "iPhone 5")
                .get("/products/search");

        // Extract product ID and assert product is found
        productId = response.jsonPath().getString("products[0].id");
        Assert.assertNotNull(productId, "Product ID should not be null");
        System.out.println("Product found: " + productId);
    }
}
