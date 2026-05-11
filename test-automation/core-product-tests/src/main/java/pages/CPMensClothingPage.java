package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePageClass;

public class CPMensClothingPage extends BasePageClass {
	
	public CPMensClothingPage(WebDriver driver) {
		super(driver);
		
	}
	
	//locators for mens clothing page
	By productGrid = By.xpath("//div[@data-trk-id='product-grid']");
	By productTitleJackets = By.xpath("//div[@class='product-card-title']//a[contains(text(),'Jacket')]");
	
	By productTitle = By.xpath("//div[@class='product-card-title']//a");
	By productPrice = By.xpath("//span[@class='lowest']//span[@class='money-value']"); // Product has 2 prices, we want the lowest price
	
	By topSellerMessage = By.xpath("//*[@class='product-vibrancy-container']"); // Not all products have this message, we will handle it in our code this contains the message
		
    By productDataContainer=By.xpath("//div[@class='product-card row']//div[@class='columns small-12 medium-12'][2]"); // Container for each product, we will use this to loop through products and extract details
    By paginationNextButton = By.xpath("//*[@class='pagination-navigation']//button[@aria-label='next page']"); // Next button for pagination, we will use this to navigate through paginated pages


   public void extractAndStoreJacketDetails() {
	   // This method will contain the logic to extract jacket details and store them in a text file
	   // We will loop through all products, check if they are jackets, and if so, extract the required details
	   
	   boolean isEnabled = false;
	   do {
		   extractDetailsFromCurrentPage(productDataContainer, productTitle, productPrice, topSellerMessage);
		   
		   // Check if the next button is enabled, if yes click it to go to the next page
		   try {
			   WebElement nextButton = findElement(paginationNextButton);
			   isEnabled = nextButton.isEnabled();
			   if (isEnabled) {
				   clickElement(paginationNextButton);
			   } else {
				   break; // No more pages to navigate
			   }
		   } catch (Exception e) {
			   System.out.println("No pagination or error navigating to next page: " + e.getMessage());
			   break; // Exit loop if pagination is not present or there's an error
		   }
	   } while (isEnabled);
	   
	   
	   
	   
   }






}
