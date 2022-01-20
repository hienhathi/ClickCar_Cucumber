package stepsDefinition;

import java.io.IOException;
import org.junit.Assert;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import pageObjects.ChecklistPage;

public class ChecklistPageSteps {
	public ChecklistPage checklistPage = new ChecklistPage(DriverFactory.getDriver());

	@Then("Verify check list page is displayed")
	public void check_out_page_is_displayed() {
		Assert.assertTrue(checklistPage.isPageDisplayed());		
	}
	
	@When("Write data to excel")
	public void write_data_to_excel() throws IOException {
		checklistPage.writeChecklistToExcel();		
	}	
}
