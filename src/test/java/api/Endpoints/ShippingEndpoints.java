package api.Endpoints;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;

import api.Payloads.CostumerDetails;
import io.restassured.response.Response;

public class ShippingEndpoints {
	
	
	public static Response addShippingAddress(ITestContext context) {
		
		CostumerDetails payload = new CostumerDetails();
		payload.setFirstname("Pranit");
		payload.setLastname("Warahikar");
		payload.setAddress_1("Mothi Umari");
		payload.setCity("Akola");
		payload.setCountry_id("India");
		payload.setZone_id("Maharashtra");
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		Response response = given()
				                .queryParam("api_token", token)
				                .formParams("firstname", payload.getFirstname())
				                .formParam("lastname", payload.getLastname())
				                .formParam("address_1", payload.getAddress_1())
				                .formParam("city", payload.getCity())
				                .formParam("country_id", payload.getCountry_id())
				                .formParam("zone_id", payload.getZone_id())
				            .when()
				                .post(Routes.post_addshippingaddress_url);
		return response;
	}

	
	
	
	public static  void setShippingMethods(ITestContext context) {
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		String shippingMethods = given()
				                   .queryParam("api_token", token)
				                .when()
				                  .post(Routes.post_getshippingmethods_url)
				                  .jsonPath().getString("shipping_methods.flat.quote.flat.code");
		context.getSuite().setAttribute("shipping_methods",shippingMethods);

	}
	
	
	
	
	
	public static Response getShippingMethods(ITestContext context) {
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		Response response = given()
				                .queryParam("api_token", token)
				            .when()
				                .post(Routes.post_getshippingmethods_url);
		return response;
		
	}
	
	
	
	
	
	public static Response SelectShippingMethod(ITestContext context) {
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		String shippingMethod = (String) context.getSuite().getAttribute("shipping_methods");
		System.out.println(shippingMethod);
		Response response = given()
				                .queryParam("api_token", token)
				                .formParam("shipping_method", shippingMethod)
				            .when()
				                .post(Routes.post_setshippingmethod_url);
		return response;
		
		
	}
	
	
}
