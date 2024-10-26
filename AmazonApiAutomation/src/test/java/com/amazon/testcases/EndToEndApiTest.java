package com.amazon.testcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EndToEndApiTest 
{
	private static String token;
    private static String productId;

    @BeforeClass
    public void setup() 
    {
        // Initialize RestAssured base URI (replace with actual API base URL)
        RestAssured.baseURI = "https://www.amazon.com";
    }

    @Test(priority = 1)
    public void loginTest() 
    {
        // Logging in with username and password
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"username\":\"your_username\", \"password\":\"your_password\"}")
                .post("/auth/login");

        // Extract token and assert login success
        token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token should not be null");
        System.out.println("Login successful. Token: " + token);
    }

    @Test(priority = 2, dependsOnMethods = "login")
    public void searchProductTest() 
    {
        // Searching for iPhone 5 product
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .queryParam("q", "iPhone 5")
                .get("/products/search");

        // Extract product ID and assert product is found
        productId = response.jsonPath().getString("products[0].id");
        Assert.assertNotNull(productId, "Product ID should not be null");
        System.out.println("Product found: " + productId);
    }

    @Test(priority = 3, dependsOnMethods = "searchProduct")
    public void addToCartTest() 
    {
        // Add the product to the cart
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{\"productId\": \"" + productId + "\", \"quantity\": 1}")
                .post("/cart/add");

        // Assert the product was added successfully
        Assert.assertEquals(response.getStatusCode(), 200, "Product should be added to cart successfully");
        System.out.println("Product added to cart successfully.");
    }

    @Test(priority = 4, dependsOnMethods = "addToCart")
    public void checkoutAndMakePaymentTest() 
    {
        // Checkout process
        Response checkoutResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .post("/cart/checkout");

        String orderId = checkoutResponse.jsonPath().getString("orderId");
        Assert.assertNotNull(orderId, "Order ID should not be null");
        System.out.println("Checkout successful. Order ID: " + orderId);

        // Payment process (assuming details are stored)
        Response paymentResponse = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{\"orderId\": \"" + orderId + "\", \"paymentMethod\": \"CREDIT_CARD\"}")
                .post("/payment/process");

        // Assert the payment was successful
        Assert.assertEquals(paymentResponse.getStatusCode(), 200, "Payment should be processed successfully");
        System.out.println("Payment completed successfully.");
    }

}
