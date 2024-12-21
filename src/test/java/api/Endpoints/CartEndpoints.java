package api.Endpoints;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;

import api.Payloads.Cart;
import io.restassured.response.Response;

public class CartEndpoints {
	
	
	public static Response addProductIncart(ITestContext context) {
		
		Cart cart = new Cart();
		cart.setProduct_id("40");
		cart.setQuantity("1");
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		System.out.println(token);
		Response response = given()
				                .queryParam("api_token",token)
				                .formParam("product_id",cart.getProduct_id())
				                .formParam("quantity", cart.getQuantity())
				           .when()
				                .post(Routes.post_addcart_url);
		return response;
				                
	}
	
	
	
	
	public static void getCartId(ITestContext context) {
		
	    String token = (String) context.getSuite().getAttribute("api_token_val");
		    Response resp = given()
				                .queryParam("api_token",token)
				           .when()
				                .post(Routes.post_getcartproducts_url);
		    
		JSONObject jo = new JSONObject(resp.asPrettyString());
		String cartid = jo.getJSONArray("products").getJSONObject(0).get("cart_id").toString();
				                
        context.getSuite().setAttribute("cart_id", cartid);
		
	}
	
	
	
	
    public static Response getCartProducts(ITestContext context) {
		
		String token = (String) context.getSuite().getAttribute("api_token_val");
		Response response = given()
				                .queryParam("api_token",token)
				           .when()
				                .post(Routes.post_getcartproducts_url);
		return response;
		
	}
    
    
    
    
    public static Response editProductQuantity(ITestContext context) {
    	
    	Cart cart = new Cart();
    	cart.setQuantity("3");
    	
    	String token = (String) context.getSuite().getAttribute("api_token_val");
    	String cartid = (String) context.getSuite().getAttribute("cart_id");
    	System.out.println(cartid);

    	Response response = given()
    			                .queryParam("api_token", token)
    			                .formParam("key", cartid)
    			                .formParam("quantity",cart.getQuantity())
    			            .when()
    			                .post(Routes.post_editcartquantity_url);
    	System.out.println(response.asPrettyString());
    	return response;
    }
    
    
    
    
    public static Response removeCartProducts(ITestContext context) {
    	
    	String token = (String) context.getSuite().getAttribute("api_token_val");
    	String cartid = (String) context.getSuite().getAttribute("cart_id");
    	Response response = given()
    			                .queryParam("api_token", token)
    			                .formParam("key", cartid)
    			            .when()
    			                .post(Routes.post_removecartproducts_url);
    	return response;
    }
	

}
