package automationExercise.api;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class ViewShoppingCart 
{
	private static String baseUrl;
	private static String token;
	
	@Test(priority = 5)
	public void checkCart() 
	{
		baseUrl = "https://www.automationexercise.com";
	    String cartEndpoint = "/cart";
	    
	    Response response = RestAssured
	    	.given()
	        .header("Authorization", "Bearer " + token)
	        .when()
	        .get(baseUrl + cartEndpoint);
	    
	    response.then().statusCode(200);
	    List<Map<String, Object>> cartItems = response.jsonPath().getList("cart_items");
	    Assert.assertTrue(cartItems.size() > 0);
	}

}
