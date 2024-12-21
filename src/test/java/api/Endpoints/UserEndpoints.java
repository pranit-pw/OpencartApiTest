package api.Endpoints;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;

import com.github.javafaker.Faker;

import api.Payloads.User;
import io.restassured.response.Response;

public class UserEndpoints {
	
	
	
    public static void createToken(ITestContext context) {
    	
    	User user = new User();
    	user.setUsername("adminpranit");
    	user.setKey("zyieORizDw5ZfZov2sKPX0WgrQx11f8ccdNwjAXC1b0p2ak7ovEGvAguH1FErzttUdwwpHfOEb84PYJLfZcrnuZmltUBQRy1nz0Qo1Df5edtorSQ7UuYcXRSugN6Boq6plBiMBzzs3QpMWTRCbmVfumpLwPSw0Koy0BCA0H2DJqIEZO7WauOAXB3VXdhm2DUUSwt2yfh5WBCpXgXibkkxAmyQXYsNIhLnGHZhpeUkDf74UocOJl7TTZ0LblWdSyD");
    	
    	String api_token = given()
    			               .formParam("username",user.getUsername())
    			               .formParam("key", user.getKey())
    			            .when()
    			               .post(Routes.post_login_url)
    			               .jsonPath().getString("api_token");    	
    	context.getSuite().setAttribute("api_token_val", api_token);
	}
    
    
     public static Response createTokenForSession() {
    	
    	Response response = given()
    			               .formParam("username")
    			               .formParam("key")
    			            .when()
    			               .post(Routes.post_login_url);
    	
		return response;
	}
     
     
     
     public static Response addCostomerDetails(ITestContext context) {
    	 
    	 Faker faker = new Faker();
    	 User user = new User();
    	 user.setFirstname(faker.name().firstName());
    	 user.setLastname(faker.name().lastName());
    	 user.setEmail(faker.internet().emailAddress());
    	 user.setTelephone(1879254802);
    	 String token = (String) context.getSuite().getAttribute("api_token_val");
    	 
    	 Response response = given()
    			                .queryParam("api_token", token)
    			                .formParam("firstname", user.getFirstname())
    			                .formParam("lastname", user.getLastname())
    			                .formParam("email", user.getEmail())
    			                .formParam("telephone", user.getTelephone())
    			            .when()
    			                .post(Routes.post_addcostuerdetails_url);
    	 return response;
     }

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
