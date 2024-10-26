package automationExercise.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class OrderConfirmation 
{
	private static String baseUrl;
	private static String token;
	private static String orderId;
	
	@Test(priority = 8)
	public void confirmOrder() 
	{
		baseUrl = "https://www.automationexercise.com";
	    String orderEndpoint = "/orders/" + orderId;
	    
	    Response response = RestAssured
	    	.given()
	        .header("Authorization", "Bearer " + token)
	        .when()
	        .get(baseUrl + orderEndpoint);
	    
	    response.then().statusCode(200);
	    String orderStatus = response.jsonPath().getString("status");
	    Assert.assertEquals(orderStatus, "CONFIRMED", "Order not confirmed");
	}

}
