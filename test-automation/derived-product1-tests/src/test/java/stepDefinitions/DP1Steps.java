package stepDefinitions;

import org.openqa.selenium.WebDriver;

import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DP1HomePage;

public class DP1Steps {

	// Do not obtain the WebDriver at class initialization time; Hooks create the driver before each scenario.
	
	WebDriver driver = DriverFactory.getDriver();
	DP1HomePage dp1HomePage;
    // Test data (could be moved to external file)
 
    @Given("I am on the DP1 home page")
    public void i_am_on_the_dp1_home_page() {
		// Initialize the page object using the driver created by Hooks
		dp1HomePage = new DP1HomePage(driver);
		// navigate to the page URL using framework helper
		dp1HomePage.navigateToDP1Homepage();
    }

    
    @When("I navigate to the Tickets menu slides and count the number of slides present")
    public void i_navigate_to_the_tickets_menu_slides_and_count_the_number_of_slides_present() {
	  // ensure dp1HomePage was initialized in the Given step
	  if (dp1HomePage == null) {
		this.dp1HomePage = new DP1HomePage(DriverFactory.getDriver());
	  }

			// call the page method that counts slides (method implemented in page object)
			dp1HomePage.getTheCountofTicketMenuSlides();
    }

    @Then("I get the title and duration of each slide")
    public void i_get_the_title_and_duration_of_each_slide() {
		 if (dp1HomePage == null) {
		   this.dp1HomePage = new DP1HomePage(DriverFactory.getDriver());
		 }

						// call page method that prints titles and durations (implemented in page object)
						dp1HomePage.getTheTitleAndDurationOfEachSlide();
       
    }

	
}
