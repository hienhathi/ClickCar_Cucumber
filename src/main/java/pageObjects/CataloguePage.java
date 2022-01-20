package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import utilities.ElementUtils;

public class CataloguePage {
	
	WebDriver driver;
	
	public CataloguePage(WebDriver driver) {
		this.driver = driver;
	}
	
	ElementUtils elementUtils = new ElementUtils(DriverFactory.getDriver());	
	
	By txtSearchNavigationBar = By.xpath("(//input[@type='search'])[1]");
	String lbCarIndexXpath = "//div[contains(@class, 'MuiContainer-root')]/div/div[2]/div[2]/div/div[index]/div/div[1]";

	String lbCarImageXpath = "//img[contains(@alt,'{partialText}')]";
	
	public void isPageDisplayed() {
		elementUtils.checkPageIsReady();
	}
	
	public void searchCarInNavigationBar(String keyword) {
		elementUtils.doSendKeys(txtSearchNavigationBar, keyword + Keys.ENTER);
	}
	
	public void clickCarByIndex(String index) {
		By ele = By.xpath(lbCarIndexXpath.replace("index", index.toUpperCase()));
		elementUtils.doClick(ele);
	}
	
	public void clickCarImageByPartialText(String partialText) {
		isPageDisplayed();
		By ele = By.xpath(lbCarImageXpath.replace("{partialText}", partialText.toUpperCase()));
		elementUtils.scrollIntoView(ele);
		elementUtils.doClick(ele);
	}
	
}
