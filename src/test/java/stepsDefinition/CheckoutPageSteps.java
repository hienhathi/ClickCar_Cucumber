package stepsDefinition;

import org.junit.Assert;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import pageObjects.CheckoutPage;

public class CheckoutPageSteps {
	public CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());;

	@Then("Verify check out page is displayed")
	public void check_out_page_is_displayed() {
		Assert.assertTrue(checkoutPage.isPageDisplayed());
		
	}
	
}
