package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonConst;

public class ElementUtils {
	
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	
	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(CommonConst.EXPLICIT_WAIT_TIMEOUT));
		action = new Actions(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			waitForElementPresent(locator);
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Exception: "  + e.getMessage() + " when get web element: " + locator);
		}
		return element;
	}

	/**
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		waitForElementPresent(locator);
		waitForElementEnabled(locator);
		WebElement element = driver.findElement(locator);
		element.click();
	}

	public void doActionsClick(By locator) {
		action.click(getElement(locator)).build().perform();
	}

	public void clearAllText(By locator) {
		getElement(locator).sendKeys(Keys.CONTROL + "A");
		getElement(locator).sendKeys(Keys.DELETE);
	}
	
	public void doSendKeys(By locator, String value) {
		clearAllText(locator);
		getElement(locator).sendKeys(value);
	}
	
	public void inputSuggessionBox(By locator, String value) {
		clearAllText(locator);
		getElement(locator).sendKeys(value + Keys.ENTER);
	}

	public void doActionsSendKeys(By locator, String value) {
		action.sendKeys(getElement(locator), value).build().perform();
	}
	
	public void sendKeysUsingJSWithId(String id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
	
	public void doMoveToElement(By locator){
		action.moveToElement(getElement(locator)).build().perform();
	}

	public boolean isElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	public boolean isElementPresent(By locator) {
		return driver.findElements(locator).size() > 0;
	}
	
	public boolean isElementBehind(By locator) {
		List<WebElement> eles = driver.findElements(locator);
		return eles.size() == 0;
	}

	public String getText(By locator) {
		return getElement(locator).getText();
	}

	public void waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}	
	
	public void waitForElementEnabled(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}	
	
	public void waitForElementVisible(By locator) {
		WebElement ele = getElement(locator);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementNotVisible(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public String getPageTitle() {
//		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public void clickElementByJS(By locator) {
		WebElement ele = getElement(locator);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", ele);
		System.out.println("======clicked  " + ele);

	}
	
	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollIntoView(By locator) {
		WebElement ele = getElement(locator);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}
	
	public void getTextByJS(By locator) {
		WebElement ele = getElement(locator);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].innerHTML;", ele);
	}
	
	public void selectOptionByPartText(By locator, String partialText) {

		WebElement ele = getElement(locator);
        Select select = new Select(ele);
        select.selectByVisibleText(partialText);
    }
	 public void refreshBrowser() {
		driver.navigate().refresh();
	 }
	public void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			return;
		}
		for (int i = 0; i < CommonConst.PAGE_LOAD_TIMEOUT; i++) {
			try {
				driver.navigate().refresh();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}
}
