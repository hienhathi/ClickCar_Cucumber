package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import utilities.ElementUtils; 

public class CheckoutPage {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	ElementUtils elementUtils = new ElementUtils(DriverFactory.getDriver());
	
	By btnBackHome = By.xpath("//span[text()='Take me back home']/parent::button");
	
	
	public Boolean isPageDisplayed() {
		elementUtils.waitForElementPresent(btnBackHome);
		String currentUrl = driver.getCurrentUrl();
		return currentUrl.contains("checkout");
	}
	
}
