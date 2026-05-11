package stepDefinitions;

import org.openqa.selenium.WebDriver;

import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CPHomePage;
import pages.CPMensClothingPage;
import pages.CPNews;

public class CPProductSelections {

	//Driver initiated in Hooks class, we are getting the driver instance here to use in our step definitions
	WebDriver driver = DriverFactory.getDriver();
	
	CPHomePage cpHomePage;
	CPMensClothingPage cpMensClothingPage;
	CPNews cpNews;
	
	@Given("user launches the CP application")
	public void user_launches_the_cp_application() {
		cpHomePage = new CPHomePage(driver);
	    cpHomePage.navigateToCPHomepage();
		
	}

	@When("user navigates to Shop Menu and select {string} category")
	public void user_navigates_to_shop_menu_and_select_category(String string) {
	   String currentwindowHandle=cpHomePage.navigateToShopMenuAndSelectCategory(string);
	   cpHomePage.movetoselectedcategoryWindow(currentwindowHandle);
	   cpMensClothingPage = new CPMensClothingPage(driver);
	   
	}

	@When("user traverses through all paginated pages")
	public void user_traverses_through_all_paginated_pages() {
		
	   cpMensClothingPage.extractAndStoreJacketDetails();
	}

	@Then("user captures all {string} details")
	public void user_captures_all_details(String string) {
		System.out.print("Capturing all " + string + " details...");
	}

	@Then("user stores details into {string}")
	public void user_stores_details_into(String string) {
	  System.out.print("Storing details into " + string + "...");
	}
	
	@When("user navigates to menu and selects {string} category")
	public void user_navigates_to_menu_and_selects_category(String string) {
	   String currentwindowHandle=cpHomePage.navigateToShopMenuAndSelectCategory("news");
	   cpHomePage.movetoselectedcategoryWindow(currentwindowHandle);
	   cpMensClothingPage = new CPMensClothingPage(driver);
	}

	@Then("user captures all {string} details greater than 3d")
	public void user_captures_all_details_greater_than_3d(String string) {
	    		cpNews = new CPNews(driver);
		cpNews.countVideoFeedsGreaterThan3D();
	}
	
}
