package api.Endpoints;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;

import api.Payloads.CostumerDetails;
import io.restassured.response.Response;
public class PaymentEndpoints {
	
	public static Response addPaymentAddress(ITestContext context) {
		
		CostumerDetails data = new CostumerDetails();
		data.setFirstname("Pranit");
		data.setLastname("Warahikar");
		data.setAddress_1("Mothi Umari");
		data.setCity("Akola");
		data.setCountry_id("India");
		data.setZone_id("Maharashtra");
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		
		Response response = given()
				               .queryParam("api_token", token)
				               .formParam("firstname", data.getFirstname())
				               .formParam("lastname", data.getLastname())
				               .formParam("address_1", data.getAddress_1())
				               .formParam("city", data.getCity())
				               .formParam("country_id", data.getCountry_id())
				               .formParam("zone_id", data.getZone_id())
				            .when()
				               .post(Routes.post_addpaymentaddress_url);
		System.out.println(response.asPrettyString());
		return response;
				                
	}
	
	
	
	
	public static void assignPaymentMethod(ITestContext context) {
		
        String token = (String) context.getSuite().getAttribute("api_token_val");
		
		String response = given()
				              .queryParam("api_token", token)
				          .when()
				              .post(Routes.post_getpaymentmethods_url)
				              .jsonPath().getString("payment_methods.cod.code");
		
		context.getSuite().setAttribute("payment_method", response);
	}
	
	
	
	
	
	public static Response getPaymentMethods(ITestContext context) {
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		
		Response response = given()
				                .queryParam("api_token", token)
				            .when()
				                .post(Routes.post_getpaymentmethods_url);
		
		System.out.println(response.asPrettyString());
		return response;
	}
	
	
	
	
	public static Response setPaymentMethod(ITestContext context) {
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		String paymentMethod = (String) context.getSuite().getAttribute("payment_method");
		
		Response response = given()
				               .queryParam("api_token", token)
				               .formParam("payment_method",paymentMethod)
				            .when()
				               .post(Routes.post_setpaymentmethod_url);
		
		System.out.println(response.asPrettyString());
		return response;
	}
	
	
	
	
	
	
	

}
