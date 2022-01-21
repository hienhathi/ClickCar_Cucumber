package factory;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import common.CommonConst;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverFactory {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the threadlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.toLowerCase().equals("chrome")) {			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
	        options.addArguments("window-size=1366,768");
	        options.addArguments("enable-automation");
			WebDriverManager.chromedriver().setup();
	        tlDriver.set(new ChromeDriver(options));
	        
		} else if (browser.toLowerCase().equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
	        options.addArguments("window-size=1366,768");
	        options.addArguments("enable-automation");					
			WebDriverManager.firefoxdriver().setup();
	        tlDriver.set(new FirefoxDriver(options));
	        
		} else if (browser.toLowerCase().equals("edge")) {	
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--headless");
	        options.addArguments("window-size=1366,768");
	        options.addArguments("enable-automation");		
			WebDriverManager.edgedriver().setup();
	        tlDriver.set(new EdgeDriver(options));
	        
		} else if (browser.toLowerCase().equals("safari")) {			
	        tlDriver.set(new SafariDriver());
	        
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(CommonConst.IMPLICIT_WAIT_TIMEOUT));
//		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CommonConst.PAGE_LOAD_TIMEOUT));
		
		return getDriver();

	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
