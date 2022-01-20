package stepsDefinition;

import factory.DriverFactory;
import io.cucumber.java.en.*;
import pageObjects.CataloguePage;

public class CataloguePageSteps {
	public CataloguePage cataloguePage = new CataloguePage(DriverFactory.getDriver());

	@When("Search and select car by keyword {string}")
	public void search_and_select_car_by_keyword(String keyword) {
		cataloguePage.searchCarInNavigationBar(keyword);
		cataloguePage.clickCarImageByPartialText(keyword);
	}	
	
}
