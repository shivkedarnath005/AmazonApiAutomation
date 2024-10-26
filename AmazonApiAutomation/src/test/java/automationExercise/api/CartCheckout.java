package automationExercise.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class CartCheckout 
{
	private static String baseUrl;
	private static String token;
	private static String orderId;
	
	@Test(priority = 6)
	public void checkoutCart() 
	{
		baseUrl = "https://www.automationexercise.com";
	    String checkoutEndpoint = "/checkout";
	    
	    Response response = RestAssured
	    	.given()
	        .header("Authorization", "Bearer " + token)
	        .when()
	        .post(baseUrl + checkoutEndpoint);
	    
	    response.then().statusCode(200);  // Assuming 200 for successful checkout initiation
	    orderId = response.jsonPath().getString("order_id");
	    Assert.assertNotNull(orderId, "Order ID is null");
	    
	}


}
