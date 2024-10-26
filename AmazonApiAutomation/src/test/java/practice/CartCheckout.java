package practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class CartCheckout 
{
	private static String baseUrl;
	private static String token;
	private static String orderId;
	
	@Test
	public void cartCheckoutTest() 
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
		
		System.out.println("Order Checked Successfully. Order ID : " + orderId);
		
					
	}
}
