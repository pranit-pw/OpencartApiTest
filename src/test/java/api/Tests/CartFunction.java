package api.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.Endpoints.CartEndpoints;
import api.Endpoints.UserEndpoints;
import api.Payloads.Cart;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CartFunction {
	
	Cart cart;
	SoftAssert soft;
	public Logger logger;
	
	@BeforeClass
	public void Setup(ITestContext context) {
		
		soft = new SoftAssert();
		
		RestAssured.useRelaxedHTTPSValidation();
		UserEndpoints.createToken(context);
		
		//Logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	
	
	
	@Test(priority = 1)
	public void addProductInCart(ITestContext context) {
		
		logger.debug("******Adding the product in cart*******");
		
         Response response = CartEndpoints.addProductIncart(context);
         
         soft.assertEquals(response.getStatusCode(),200);
         soft.assertEquals(response.jsonPath().getString("success"), "Success: You have modified your shopping cart!");
         soft.assertAll();
         
        logger.debug("******Product is added in cart*******"); 
         
	}
	
	
	
	
	@Test(priority = 2)
	public void getCartProducts(ITestContext context) throws InterruptedException {
		
		logger.debug("******Getting cart product details *******");
		
		Response resp = CartEndpoints.getCartProducts(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("products.product_id"), "[40]");
		soft.assertEquals(resp.jsonPath().getString("products.quantity"), "[1]");
		soft.assertAll();
		CartEndpoints.getCartId(context);
		Thread.sleep(2000);
		
		logger.debug("******Product is fetched successfully*******");
	}

	
	
	
	@Test(priority = 3)
	public void editProductsQuantity(ITestContext context) {
		
		logger.debug("******editing product quantity in cart*******");
		
		Response resp = CartEndpoints.editProductQuantity(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("success"), "Success: You have modified your shopping cart!");
		soft.assertAll();
		
		logger.debug("******Product Quantity is edited*******");
	}
	
	
	
	
	
	@Test(priority = 4)
	public void removeCartProduct(ITestContext context) {
		
		logger.debug("******Removing Product From Cart*******");
		
		Response resp = CartEndpoints.removeCartProducts(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("success"), "Success: You have modified your shopping cart!");
		soft.assertAll();
		
		logger.debug("******Product is removed from cart*******");
	}
	
	
	
	
	
	
	
}