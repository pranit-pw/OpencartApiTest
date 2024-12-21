package api.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.Endpoints.CartEndpoints;
import api.Endpoints.ShippingEndpoints;
import api.Endpoints.UserEndpoints;
import io.restassured.response.Response;

public class ShippingMethod {
	
	SoftAssert soft;
	public Logger logger;
    
	@BeforeClass
	public void setdata() {
		
		soft = new SoftAssert();
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	
	
	
	@Test(priority = 1)
	public void addShippingAddress(ITestContext context) {
		
		logger.debug("******Adding shipping address*******");
		
		CartEndpoints.addProductIncart(context);
		Response resp = ShippingEndpoints.addShippingAddress(context);
		
		soft.assertEquals(resp.getStatusCode(),200);
		soft.assertEquals(resp.jsonPath().getString("success"), "Success: Shipping address has been set!");
		soft.assertAll();
		
		logger.debug("******Shipping address is added*******");
		
	}
	
	
	
	@Test(priority = 2)
	public void getShippingMethods(ITestContext context) throws Exception {
		
		logger.debug("******Getting shipping methods*******");
		
		Response resp = ShippingEndpoints.getShippingMethods(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("shipping_methods.flat.title"), "Flat Rate");
		soft.assertAll();
		Thread.sleep(1000);
		ShippingEndpoints.setShippingMethods(context);
		
		logger.debug("******Shipping methods are fetched successfully*******");
		
	}
	
	
	
	
	@Test(priority = 3)
	public void setSippingMethod(ITestContext context) {
		
		logger.debug("******Selecting shipping method*******");
		
		Response resp = ShippingEndpoints.SelectShippingMethod(context);
		
		System.out.println(resp.asPrettyString());
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("success"), "Success: Shipping method has been set!");
		soft.assertAll();
		
		logger.debug("******Shipping method is selected*******");
		
	}
	
	
	
	
	
	@Test(priority = 4)
	public void addCostumerDetails(ITestContext context) {
		
		logger.debug("******Adding costumer details*******");
		
		Response resp = UserEndpoints.addCostomerDetails(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("success"), "You have successfully modified customers");
		soft.assertAll();
		
		logger.debug("******Costumer details is added successfully*******");
	}
	
	
	
	
	
	
	
	
}
