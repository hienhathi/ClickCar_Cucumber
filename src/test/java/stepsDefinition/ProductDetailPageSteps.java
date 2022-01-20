package stepsDefinition;

import org.junit.Assert;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import pageObjects.ProductDetailPage;

public class ProductDetailPageSteps {

	public ProductDetailPage productDetailPage = new ProductDetailPage(DriverFactory.getDriver());
	
	@When("User buy current car")
	public void user_buy_current_car() {
		productDetailPage.buyProduct();
	}

	@Then("Verify Product detail page is displayed")
	public void product_detail_page_is_displayed() {
	    Assert.assertTrue(productDetailPage.isPageDisplayed());
	}
	
}
