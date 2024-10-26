package automationExercise.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class LoginTest 
{
	private static String baseUrl;
	private static String token;
	
	@Test(priority = 2)
	public void userLogin() 
	{
		baseUrl = "https://www.automationexercise.com";
	    String loginEndpoint = "/login";
	    
	    Map<String, Object> loginDetails = new HashMap<>();
	    loginDetails.put("email", "john.doe@test.com");
	    loginDetails.put("password", "password123");
	    
	    Response response = RestAssured
	    	.given()
	        .contentType(ContentType.JSON)
	        .body(loginDetails)
	        .when()
	        .post(baseUrl + loginEndpoint);
	    
	    response.then().statusCode(200);  // Assuming successful login
	    token = response.jsonPath().getString("token");
	    Assert.assertNotNull(token, "Login token is null");
	}
}
