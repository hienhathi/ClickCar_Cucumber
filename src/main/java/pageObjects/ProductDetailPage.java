package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import utilities.ElementUtils;

public class ProductDetailPage {
	
	WebDriver driver;
	
	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
	}

	ElementUtils elementUtils = new ElementUtils(DriverFactory.getDriver());	
	
	By txtPostCode = By.xpath("//input[@aria-autocomplete='list']");
	By btnSetLocation = By.xpath("//span[text()='Set location']/..");
	By btnAddPostCode = By.xpath("//*[text()='Add']/../parent::button");
	By btnEditPostCode = By.xpath("//*[text()='Edit']/../parent::button");
	By btnBuyNow = By.xpath("//span[text()='Buy now']/parent::button");
	By btnContinue = By.xpath("//span[text()='Continue']/parent::button");
	By btnGoback = By.xpath("//span[text()='Go back']/parent::button");
	
	public void setPostCode(String postCode) {	
		elementUtils.doClick(btnAddPostCode);	
		inputPostCode(postCode);
		elementUtils.doClick(btnSetLocation);		
	}
	
	public void inputPostCode(String postCode) {
		String id = elementUtils.getElement(txtPostCode).getAttribute("id");
		System.out.println("-----1-" + id);
		elementUtils.clickElementByJS(txtPostCode);
		elementUtils.doSendKeys(txtPostCode, postCode);
		elementUtils.doActionsSendKeys(txtPostCode, postCode);
		String id2 = elementUtils.getElement(txtPostCode).getAttribute("id");
		System.out.println("-----2-" + id2);
		By txtPostCodeFirstOption = By.xpath("//input[@id='" + id + "-option-0']");
		elementUtils.doClick(txtPostCodeFirstOption);
	}
	
	public CheckoutPage buyProduct() {
		elementUtils.doClick(btnBuyNow);
		elementUtils.doClick(btnContinue);
		return new CheckoutPage(driver);
		
	}
	
	public Boolean isPageDisplayed() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl.contains("product-detail");
	}
	
}
