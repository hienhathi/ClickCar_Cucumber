package pageObjects;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChecklistPage {
	
	WebDriver driver;
	
	public ChecklistPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By lbItems = By.xpath("//p[@class='MuiTypography-root font-bold MuiTypography-body2']");
	By lbBigItems = By.xpath("//p[@class='MuiTypography-root font-bold MuiTypography-body1']");
	String lbSubItemsXpath = "(//p[@class='MuiTypography-root font-bold MuiTypography-body1'])[index]/../../../following-sibling::div//p[@class='MuiTypography-root font-bold MuiTypography-body2']";
	
	
	public Boolean isPageDisplayed() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl.contains("checklist");
	}
	
	public void navigateTo() {
		driver.get("https://staging.d6w1nkpc3z1lk.amplifyapp.com/checklist");
	}
	
	public void writeChecklistToExcel() throws IOException {
		navigateTo();
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Emp Info");
		
		List<WebElement> bigItems = driver.findElements(lbBigItems);
		/// using for...each loop
		int rownum=0;
		for (int j = 0; j < bigItems.size(); j ++) {

			List<WebElement> subItems = driver.findElements(By.xpath(lbSubItemsXpath.replace("index",Integer.toString(j+1))));
			for (int i = 0; i < subItems.size(); i ++) {
		    	  XSSFRow row=sheet.createRow(rownum++);
		    	  XSSFCell cell1=row.createCell(1);
		    	  XSSFCell cell2=row.createCell(2);
		    	  String value1 = bigItems.get(j).getAttribute("innerHTML");
		    	  String value2 = subItems.get(i).getAttribute("innerHTML");
		    	  cell1.setCellValue(value1);
		    	  cell2.setCellValue(value2);
			}
		}	
	      
		String filePath="./ItemList.xlsx";
		FileOutputStream outstream=new FileOutputStream(filePath);
		workbook.write(outstream);		
		outstream.close();
		
		System.out.println("ItemList.xls file written successfully...");
	}
	
}
