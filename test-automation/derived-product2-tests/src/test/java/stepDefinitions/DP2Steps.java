package stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DP2HomePage;

public class DP2Steps {

	// Initialize the page object using the driver created by Hooks
	WebDriver driver = DriverFactory.getDriver();

	DP2HomePage dp2HomePage;
	List<String> footerLinks; // To store the links retrieved from the footer for later verification
	Map<String, Set<String>> footerLinksInfo; // To store any duplicate links found for reporting

	@Given("I am on the DP2 home page")
	public void i_am_on_the_dp2_home_page() {

		dp2HomePage = new DP2HomePage(driver);

		dp2HomePage.navigateToDP1Homepage();
	}

	@When("I scroll down to the footer of the DP2 home page and get all the links present in the footer section")
	public void i_scroll_down_to_the_footer_of_the_dp2_home_page_and_get_all_the_links_present_in_the_footer_section() {

		if (dp2HomePage == null) {
			this.dp2HomePage = new DP2HomePage(DriverFactory.getDriver());
		}

		// call the page method that gets all footer links (method implemented in page
		// object)
		footerLinks = dp2HomePage.getAllFooterLinks();
	}

	@Then("I verify if there are any duplicate links present in the footer section of the DP2 home page")
	public void i_verify_if_there_are_any_duplicate_links_present_in_the_footer_section_of_the_dp2_home_page() {
		if (dp2HomePage == null) {
			this.dp2HomePage = new DP2HomePage(DriverFactory.getDriver());
		}
		footerLinksInfo = dp2HomePage.verifyDuplicateLinksInFooter(footerLinks);

	}

	@Then("I store the links to the CSV file with the name {string}")
	public void i_store_the_links_to_the_csv_file_with_the_name(String string) {
		if (dp2HomePage == null) {
			this.dp2HomePage = new DP2HomePage(DriverFactory.getDriver());
		}
		dp2HomePage.writeFooterLinksToCSV(footerLinksInfo.get("Unique"), string);
	}

}
