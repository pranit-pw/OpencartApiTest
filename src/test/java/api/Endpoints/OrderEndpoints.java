package api.Endpoints;

import org.testng.ITestContext;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class OrderEndpoints {

	
	public static Response addOrder(ITestContext context) {
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		
		Response response = given()
				                .queryParam("api_token", token)
				            .when()
				                .post(Routes.post_addorder_url);
		
		return response;
				                 
	}
	
    public static Response editOrder(ITestContext context) {
		
		 String token = (String) context.getSuite().getAttribute("api_token_val");

		 Response response = given()
		 		                .queryParam("api_token", token)
				            .when()
				                .post(Routes.post_editorder_url);
		 return response;
	}
}
