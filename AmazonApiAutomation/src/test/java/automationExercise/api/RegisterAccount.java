package automationExercise.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class RegisterAccount 
{
	private static String baseUrl;
	
	@Test(priority = 1)
	public void userRegistration() 
	{
		baseUrl = "https://www.automationexercise.com";
	    String registerEndpoint = "/register";
	    
	    Map<String, Object> userDetails = new HashMap<>();
	    userDetails.put("name", "John Doe");
	    userDetails.put("email", "john.doe@test.com");
	    userDetails.put("password", "password123");
	    
	    Response response = RestAssured
	    	.given()
	        .contentType(ContentType.JSON)
	        .body(userDetails)
	        .when()
	        .post(baseUrl + registerEndpoint);
	    
	    response.then().statusCode(201);  // Assuming 201 status code for successful registration
	    String userId = response.jsonPath().getString("userId");
	    Assert.assertNotNull(userId, "User ID is null");
	}
}
