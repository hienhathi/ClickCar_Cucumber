package stepsDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import utilities.HttpRequest;
import utilities.XLUtility;
import pageObjects.HomePage;
import common.CommonConst; 

public class LoginPageSteps {
	public LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	public HomePage homePage = new HomePage(DriverFactory.getDriver());
	ArrayList<List<String>> loginDatas;
	int failCount = 0;
	@Given("User is on Login page")
	public void user_is_on_login_page() {
		homePage.refresh();
		homePage.clickLoginStart();
		homePage.clickLogin();
//		String token = HttpRequest.getToken();
//		System.out.println("====token: " + token);
	}

	@When("User input default email and password")
	public void user_input_default_email_and_password() {
		loginPage.inputEmail(CommonConst.DEFAULT_USERNAME);
		loginPage.inputPassword(CommonConst.DEFAULT_PASSWORD);
	}

	@When("User input email {string} and password {string}")
	public void user_input_email_and_password(String email, String password) {
		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
	}
	
	@When("User click Login button")
	public void user_click_login_button() {
		loginPage.clickLogin();
	}	
	
	@Then("Verify that log in status is {string}")
	public String verify_that_log_in_status_is(String loginStatus) throws InterruptedException {
		String result = "Failed";
		if (loginStatus.toLowerCase().equals("success")) {
			Assert.assertFalse(loginPage.isLoginSuccess());
			result = "Passed";
		} else {
			Assert.assertTrue(loginPage.isLoginFailed());
			result = "Passed";
		}
		return result;
	}
	
	@Then("User login successfully")
	public void user_login_successfully() throws InterruptedException {
		Thread.sleep(1000);		
		Boolean isDisplayed = DriverFactory.getDriver().findElements(By.xpath("(//button//*[text()='Login'])[2]")).size()>0;
		Assert.assertFalse(isDisplayed);
	}
	
	@When("User login with data from excel with file name {string} and sheet name {string}")
	public void doLoginWithDataFile(String fileName, String sheetName) throws IOException, InterruptedException {
		XLUtility xlutil=new XLUtility(fileName, sheetName);
		loginDatas = xlutil.getData();	
		loginDatas.get(0).add("Result");	// add Result column
		
		for (List<String> data: loginDatas.subList(1,loginDatas.size())) { // get all data except the first one (Column name)
			user_is_on_login_page();
	    	String email = data.get(0);
	    	String password = data.get(1);
	    	String status = data.get(2);
	    	loginPage.doLogin(email, password);
	    	String result = verify_that_log_in_status_is(status);
	    	data.add(result);
	    	if (status == "Success") {
		    	loginPage.logout();
	    	}
	    	if (result.equalsIgnoreCase("Failed")) {
	    		failCount++;
	    	}
		}
//    	xlutil.writeDataToExcel(loginDatas);	
//		Assert.assertTrue(failCount == 0);
	}

	@Then("Data result file is generated successfully with file name {string}_result and sheet name {string}")
	public void data_result_file_is_generated(String fileName, String sheetName) throws IOException {
		XLUtility xlutil=new XLUtility(fileName, sheetName);
    	xlutil.writeDataToExcel(loginDatas);	
		Assert.assertTrue(failCount == 0);
	}
}
