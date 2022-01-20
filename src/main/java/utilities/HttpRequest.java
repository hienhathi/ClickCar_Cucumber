package utilities;

import java.io.File;
import java.util.Properties;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import common.Endpoint;

public class HttpRequest {

	private static final String RESOURCE_PATH = "src/test/resources/requestBody/";
	private static ConfigReader configReader = new ConfigReader();;
	private static Properties prop = configReader.init_prop();
	private static String baseURL = prop.getProperty("apiURL");	
	
	public static String getToken() {
		RestAssured.baseURI = baseURL;
		File file = new File(RESOURCE_PATH + "login.json");
	    Response response = RestAssured.given()
      		  .header("Content-Type", "application/json")
      		  .body(file)
      		  .post(Endpoint.SIGNIN_ENDPOINT);
        String token = response.getBody().jsonPath().getString("access_token");
        return token;
	}
	
	public static Response get(String endpoint) {
		String token = getToken();	
		RestAssured.baseURI = baseURL;
        Response response = RestAssured.given()
      		  .header("Content-Type", "application/json")
      		  .header("X-Auth-Token", token)
      		  .when()
              .get(endpoint);
		return response;
    }
	
	
	public static Response post(String endpoint, String bodyFileName) {	
		String token = getToken();	
		RestAssured.baseURI = baseURL;
		File file = new File(RESOURCE_PATH + bodyFileName);
        Response response = RestAssured.given()
      		  .header("Content-Type", "application/json")
      		  .header("Bearer", token)
      		  .body(file)
      		  .when()
      		  .post(endpoint);         
        return response;
    }
	
	public static Response put(String endpoint, String bodyFileName) {	
		String token = getToken();	
		RestAssured.baseURI = baseURL;
		File file = new File(RESOURCE_PATH + bodyFileName);
        Response response = RestAssured.given()
      		  .header("Content-Type", "application/json")
      		  .header("Bearer", token)
      		  .body(file)
      		  .when()
      		  .put(endpoint);           
        return response;
    }
	
	public static Response delete(String endpoint, JSONObject requestparams) {
		String token = getToken();	
		RestAssured.baseURI = baseURL;
        Response response = RestAssured.given()
      		  .header("Content-Type", "application/json")
      		  .header("Bearer", token)
      		  .body(requestparams.toJSONString())
      		  .when()
      		  .put(endpoint);          
        return response;
    }
}

