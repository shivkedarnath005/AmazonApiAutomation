package automationExercise.api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class AddToCart 
{
	private static String baseUrl;
	private static String token;
	private static String productId;
	
	@Test(priority = 4)
	public void addToCart() 
	{
		baseUrl = "https://www.automationexercise.com";
	    String cartEndpoint = "/cart/add";
	    
	    Map<String, Object> cartItem = new HashMap<>();
	    cartItem.put("product_id", productId);
	    cartItem.put("quantity", 1);
	    
	    Response response = given()
	        .header("Authorization", "Bearer " + token)
	        .contentType(ContentType.JSON)
	        .body(cartItem)
	        .when()
	        .post(baseUrl + cartEndpoint);
	    
	    response.then().statusCode(201);
	}

}
