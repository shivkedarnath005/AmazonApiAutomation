package com.amazon.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class CheckoutAndPayment 
{
	private static String token;
	
	@Test
    public void checkoutPaymentTest() 
	{
		
		RestAssured.baseURI = "https://www.amazon.com/";
		
		 // Checkout process
        Response checkoutResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .post("/cart/checkout");

        String orderId = checkoutResponse.jsonPath().getString("orderId");
        Assert.assertNotNull(orderId, "Order ID should not be null");
        System.out.println("Checkout successful. Order ID: " + orderId);
        
        Map<String,Object> paymentDetails = new HashMap<>();
		paymentDetails.put("productId", orderId);
		paymentDetails.put("quantity", 2);

        // Payment process (assuming details are stored)
        Response paymentResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(paymentDetails)
                .post("/payment/process");

        // Assert the payment was successful
        Assert.assertEquals(paymentResponse.getStatusCode(), 200);
        System.out.println("Payment completed successfully.");
    }
}
