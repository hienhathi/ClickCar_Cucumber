package stepsDefinition;

import factory.DriverFactory;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import pageObjects.CataloguePage;
import pageObjects.HomePage;
import common.CommonConst;

public class HomePageSteps {
	public LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	public HomePage homePage= new HomePage(DriverFactory.getDriver());
	public CataloguePage cataloguePage= new CataloguePage(DriverFactory.getDriver());
//	private ProductDetailPage productDetailPage = new ProductDetailPage(DriverFactory.getDriver());
	
	@Given("User login successfully with default credentials")
	public void user_login_successfully_with_default_credentials() throws InterruptedException {
		homePage.clickLoginStart();
		homePage.clickLogin();
		loginPage.doLogin(CommonConst.DEFAULT_USERNAME, CommonConst.DEFAULT_PASSWORD);
		loginPage.waitForLoginSuccessFully();
		homePage.clickCloseChatFrame();
	}
	
	@When("User go to Catalogue page")
	public void user_go_to_Catalogue_page() {
		homePage.clickBrowserCar();
	}

	@When("User get car from home page with model {string} and make {string}")
	public void user_search_car_in_home_page(String make, String model) {
		homePage.setLocation("Darlinghurst 2010 NSW");
		homePage.selectMake(make);
		homePage.selectModel(model);
		homePage.clickSearch();
		String carInfo = make + " " + model;
		cataloguePage.clickCarImageByPartialText(carInfo);
//		productDetailPage.setPostCode("Darlinghurst 2010 NSW");
	}
	
}
