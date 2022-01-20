package common;

public class Endpoint {
	public static String SIGNUP_ENDPOINT = "/api/v2/customer/signup";
	public static String CUTOMER_VERIFICATION_ENDPOINT = "/api/v2/customer/verification";
	public static String SIGNIN_ENDPOINT = "/api/v2/customer/signin";
	public static String ANONYMOUS_TOKEN_ENDPOINT = "/api/v2/anonymous/token";
	public static String PASSWORD_TOKEN_ENDPOINT = "/api/v2/password/token";
	public static String PASSWORD_RESET_ENDPOINT = "/api/v2/password/reset";
	public static String PROFILE_ENDPOINT = "/api/v2/me/profile";
	public static String PHONE_VERIFICATION_ENDPOINT = "/api/v2/me/profile/phone/verification";
	public static String CART_ENDPOINT = "/api/v2/me/cart";
	public static String CART_UPDATE_ENDPOINT = "/api/v2/me/cart/{cartId}";
	public static String PAYMENT_METHOD_ENDPOINT = "/api/v2/payments/methods";
	public static String CART_PAYMENT_ENDPOINT = "/api/v2/me/carts/{cartId}/payments";
	public static String ORDER_PAYMENT_ENDPOINT = "/api/v2/me/orders/{orderId}/payments";
	public static String SKU_IMAGE_ENDPOINT = "/api/v2/vehicles/{skuId}/images";
	
}
