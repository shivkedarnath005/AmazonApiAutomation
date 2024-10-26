package automationExercise.api;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class SearchProduct 
{
	private static String baseUrl;
	private static String productId;
	
	@Test(priority = 3)
	public void searchProduct() 
	{
		baseUrl = "https://www.automationexercise.com";
	    String searchEndpoint = "/products/search";
	    
	    Response response = given()
	        .queryParam("search", "T-shirts")
	        .when()
	        .get(baseUrl + searchEndpoint);
	    
	    response.then().statusCode(200);
	    List<Map<String, Object>> products = response.jsonPath().getList("products");
	    Assert.assertTrue(products.size() > 0);
	    
	    productId = products.get(0).get("id").toString();  // Save product ID for future use
	    System.out.println("Product found: " + productId);
	}

}
