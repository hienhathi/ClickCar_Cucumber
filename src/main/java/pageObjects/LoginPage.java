package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.Message;
import factory.DriverFactory;
import utilities.ElementUtils;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	ElementUtils elementUtils = new ElementUtils(DriverFactory.getDriver());	

	private By txtEmail = By.name("username");
	private By txtPassword = By.name("password");
	private By btnLogin = By.xpath("(//button//*[text()='Login'])[2]");
	private By btnCreateAccount = By.xpath("//button//*[text()='Create an account']");
	private By lbErrorMessage = By.xpath("//div[@class='MuiAlert-message']");
	private By btnProfile = By.xpath("//div[contains(@class, 'MuiPaper-root')]/div/div[2]/div");
	private By btnSignout = By.xpath("//div[text()='Sign out']");
	public void logout() {
		elementUtils.doClick(btnProfile);
		elementUtils.doClick(btnSignout);		
	}
	public void inputEmail(String email) {	
		elementUtils.doSendKeys(txtEmail, email);
	}
	
	public void inputPassword(String password) {
		elementUtils.doSendKeys(txtPassword, password);
	}
	
	public void clickLogin() {
		elementUtils.doClick(btnLogin);
	}
	
	public HomePage doLogin(String email, String password) {
		elementUtils.doSendKeys(txtEmail, email);
		elementUtils.doSendKeys(txtPassword, password);
		elementUtils.doClick(btnLogin);
		return new HomePage(driver);
	}
	public void clickCreateAccount() {
		elementUtils.doClick(btnCreateAccount);
	}
	
	public String getErrorMessage() {
		return elementUtils.getText(lbErrorMessage);
	}
	
	public Boolean isLoginSuccess() throws InterruptedException {
//		Thread.sleep(1500);
		return elementUtils.isElementBehind(btnLogin);
	}
	
	public Boolean isLoginFailed() {
		Boolean isTrue = false;
		String errMessage = getErrorMessage();
		isTrue = elementUtils.isElementDisplayed(btnLogin) 
				&& errMessage.contains(Message.LOGIN_EMAIL_OR_PASSWORD_IS_INCORRECT);
		return isTrue;
	}
	
//	@Test(dataProvider="LoginData")
//	public void loginTest(String user,String pwd,String exp)
//	{
//		driver.get("https://admin-demo.nopcommerce.com/login");
//		
//		WebElement txtEmail=driver.findElement(By.id("Email"));
//		txtEmail.clear();
//		txtEmail.sendKeys(user);
//		
//		
//		WebElement txtPassword=driver.findElement(By.id("Password"));
//		txtPassword.clear();
//		txtPassword.sendKeys(pwd);
//		
//		driver.findElement(By.xpath("//input[@value='Log in']")).click(); //Login  button
//		
//		String exp_title="Dashboard / nopCommerce administration";
//		String act_title=driver.getTitle();
//		
//		if(exp.equals("Valid"))
//		{
//			if(exp_title.equals(act_title))
//			{
//				driver.findElement(By.linkText("Logout")).click();
//				Assert.assertTrue(true);
//			}
//			else
//			{
//				Assert.assertTrue(false);
//			}
//		}
//		else if(exp.equals("Invalid"))
//		{
//			if(exp_title.equals(act_title))
//			{
//				driver.findElement(By.linkText("Logout")).click();
//				Assert.assertTrue(false);
//			}
//			else
//			{
//				Assert.assertTrue(true);
//			}
//		}
//		
//	}
//	
//	@DataProvider(name="LoginData")
//	public String [][] getData() throws IOException
//	{
//		/*String loginData[][]= {
//								{"admin@yourstore.com","admin","Valid"},
//								{"admin@yourstore.com","adm","Invalid"},
//								{"adm@yourstore.com","admin","Invalid"},
//								{"adm@yourstore.com","adm","Invalid"}
//							};*/
//		
//		//get the data from excel
//		String path=".\\datafiles\\loginData.xlsx";
//		XLUtility xlutil=new XLUtility(path);
//		
//		int totalrows=xlutil.getRowCount("Sheet1");
//		int totalcols=xlutil.getCellCount("Sheet1",1);	
//				
//		String loginData[][]=new String[totalrows][totalcols];
//			
//		
//		for(int i=1;i<=totalrows;i++) //1
//		{
//			for(int j=0;j<totalcols;j++) //0
//			{
//				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
//			}
//				
//		}
//		
//		return loginData;
//	}

//	public void doLoginWithDataFile(String fileName, String sheetName) throws IOException {
//		XLUtility xlutil=new XLUtility(fileName, sheetName);
//		String [][] loginDatas = xlutil.getData();
//		String[][] results = Arrays.copyOf(loginDatas, loginDatas.length + 1); // New array with row size of old array + 1
//		for (String[] u: results) {
//		    for (int i = 0; i < u.length; i ++) {
//		    	String email = u[1];
//		    	String password = u[2];
//		    	String status = u[3];
//		    	doLogin(email, password);
//		    	
//
//		    }
//		}
//	}
	
}
