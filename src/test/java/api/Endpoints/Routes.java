package api.Endpoints;

public class Routes {
	
    public static String base_url = "https://192.168.31.207/opencart/upload/index.php?route=";
	
	// login url
	public static String post_login_url = base_url+"api/login";
	public static String get_url = base_url+"api";
	public static String put_url = base_url+"api";
	public static String delete_url = base_url+"api";
	
	//Add to cart url
	public static String post_addcart_url = base_url+"api/cart/add";
	//Get cart products
	public static String post_getcartproducts_url = base_url+"api/cart/products";
	//Edit cart product quantity
	public static String post_editcartquantity_url = base_url+"api/cart/edit";
	//remove cart product
	public static String post_removecartproducts_url = base_url+"api/cart/remove";
	
	
	//Add Shipping address
	public static String post_addshippingaddress_url = base_url+"api/shipping/address";
	//Get shipping Methods
	public static String post_getshippingmethods_url = base_url+"api/shipping/methods";
	//Set shipping method
	public static String post_setshippingmethod_url = base_url+"api/shipping/method";
	
	
	//Add Costumer details 
	public static String post_addcostuerdetails_url = base_url+"api/customer";
	
	
	//Add payment address
	public static String post_addpaymentaddress_url = base_url+"api/payment/address";
	//Get payment Methods
	public static String post_getpaymentmethods_url = base_url+"api/payment/methods";
	//Set payment method
	public static String post_setpaymentmethod_url = base_url+"api/payment/method";
	
	//Add order
	public static String post_addorder_url = base_url+"api/order/add";
	//Edit order
	public static String post_editorder_url = base_url+"api/order/edit";
	
	
}
