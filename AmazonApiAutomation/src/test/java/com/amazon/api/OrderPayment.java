package com.amazon.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class OrderPayment 
{
	private static String baseUrl;
	private static String token;
	private static String orderId;
	private static String paymentStatus;
	
	@Test
	public void orderPaymentTest() 
	{
		baseUrl ="www.amazon.com";
		String paymentEndpoint = "/order/payment";
		
		Map<String,Object> paymentDetails = new HashMap<>();
		paymentDetails.put("order_Id", orderId);
		paymentDetails.put("card_number", "89124536687");
		paymentDetails.put("expiry_date", "12/25");
		paymentDetails.put("cvv", 123);
		
		Response response = RestAssured
				.given()
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.body(paymentDetails)
				.when()
				.post(baseUrl + paymentEndpoint);
		
		response.then().statusCode(200);
		paymentStatus = response.jsonPath().getString("status");
		Assert.assertEquals(paymentStatus, "SUCCESS", "Payment Failed");
		
		
		
	}
}
