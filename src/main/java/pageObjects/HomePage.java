package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import utilities.ElementUtils;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public ElementUtils elementUtils = new ElementUtils(DriverFactory.getDriver());
	
	private By btnLoginStart = By.xpath("(//button//*[text()='Login'])[1]");
	private By btnLogin = By.xpath("(//button//*[text()='Login'])[2]");
	private By btnCreateAccount = By.xpath("//button//*[text()='Create an account']");
	private By btnSetLocation = By.xpath("//*[text()='Set location']/../..");
	private By cbMake = By.xpath("(//option[@value='all']/..)[1]");
	private By cbModel = By.xpath("(//option[@value='all']/..)[2]");
	private By btnSearchCar = By.xpath("//*[contains(text(), 'Search')]/parent::button");
	private By txtPostCode = By.xpath("//input[@aria-autocomplete='list']");
	private By btnConfirmLocation = By.xpath("//span[text()='Set location']/..");
	private By btnBrowserCars = By.xpath("//p[text()='Browse cars']");
	
	By btnCloseChat = By.xpath("//div[@id='sp-close-frame']");
	By btnSubmitChat = By.id("submitChat");
	
	public void clickCloseChatFrame() throws InterruptedException {
		if (driver.findElements(btnSubmitChat).size() > 0) {
			elementUtils.doClick(btnCloseChat);
		}
	}
	
	
	public void clickLoginStart() throws InterruptedException {
		elementUtils.checkPageIsReady();
		clickCloseChatFrame();
		elementUtils.doClick(btnLoginStart);
	}
	
	public void clickLogin() {
		elementUtils.doClick(btnLogin);
	}
	
	public void clickCreateAccount() {
		elementUtils.doClick(btnCreateAccount);
	}
	
	public void clickBrowserCar() {
		elementUtils.doClick(btnBrowserCars);
	}
	public void selectMake(String partialText) {
		elementUtils.checkPageIsReady();
		elementUtils.selectOptionByPartText(cbMake, partialText);
	}
	
	public void selectModel(String partialText) {
		elementUtils.selectOptionByPartText(cbModel, partialText);
	}
	
	public CataloguePage clickSearch() {
		elementUtils.clickElementByJS(btnSearchCar);
		return new CataloguePage(driver);
	}
	
	public void refresh() {
		driver.navigate().refresh();
	}
	public void setLocation(String postCode) {
		elementUtils.checkPageIsReady();
		elementUtils.clickElementByJS(btnSetLocation);
		elementUtils.inputSuggessionBox(txtPostCode, postCode);
		elementUtils.doClick(btnConfirmLocation);
	}
}
