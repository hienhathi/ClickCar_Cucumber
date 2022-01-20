package appHooks;

import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import factory.DriverFactory;
import utilities.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;

	public static WebDriverWait wait;
	
	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		System.out.println("getProperty");
	}

	@Before(order = 1)
	public void launchBrowser() throws InterruptedException {
		driverFactory = new DriverFactory();
		
		String configBrowser = prop.getProperty("browser");
		String browserParamFromEnv = System.getProperty("browser");
		String driverBrowser = browserParamFromEnv == null ? configBrowser : browserParamFromEnv;
		driver = driverFactory.init_driver(driverBrowser);

		String configUrl = prop.getProperty("URL");
		String urlParamFromEnv = System.getProperty("URL");
		String driverUrl = urlParamFromEnv == null ? configUrl : urlParamFromEnv;
		driver.get(driverUrl);
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			System.out.println("tearDown");
		}
	}

}