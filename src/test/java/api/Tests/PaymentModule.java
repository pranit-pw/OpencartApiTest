package api.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.Endpoints.PaymentEndpoints;
import io.restassured.response.Response;

public class PaymentModule {
	
	SoftAssert soft;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		soft = new SoftAssert();
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}

	
	@Test(priority = 1)
	public void addPaymentAddress(ITestContext context) {
		
		logger.debug("******Adding payment address*******");
		
		Response resp = PaymentEndpoints.addPaymentAddress(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("success"), "Success: Payment address has been set!");
		soft.assertAll();
		
		logger.debug("******Payment address is added*******");
	}
	
	
	
	
	
	@Test(priority = 2)
	public void getPaymentMethods(ITestContext context) throws Exception {
		
		logger.debug("******Getting payment methods*******");
		
		Response resp = PaymentEndpoints.getPaymentMethods(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("payment_methods.cod.title"), "Cash On Delivery");
		soft.assertAll();
		
		PaymentEndpoints.assignPaymentMethod(context);
		Thread.sleep(1000);
		
		logger.debug("******Payment methods are fetched successfully*******");
	}
	
	
	
	
	@Test(priority = 3)
	public void setPaymentMethod(ITestContext context) {
		
		logger.debug("******Selecting the payment method*******");
		
		Response resp = PaymentEndpoints.setPaymentMethod(context);
		
		soft.assertEquals(resp.getStatusCode(), 200);
		soft.assertEquals(resp.jsonPath().getString("success"), "Success: Payment method has been set!");
		soft.assertAll();
		
		logger.debug("******Payment method is selected successfully*******");
	}
	
	
}
