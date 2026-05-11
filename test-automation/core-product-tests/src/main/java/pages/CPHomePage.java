package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePageClass;

public class CPHomePage extends BasePageClass {
	
	//Initialize the driver in the constructor: call the parent's constructor so the BasePageClass
	//can initialize its protected driver field. Do NOT rely on assigning super.driver here.
	public CPHomePage(WebDriver driver) {
		super(driver);
	}
	
//	●	Test Case 1: for CP
//	○	From the CP home page , go to >> Shop Menu >> Men’s
//	○	Find all Jackets ( from all paginated pages)
//	○	Store each Jacket Price, Title and Top Seller message to a text file
//	○	Attach the text file to the report

	
//	●	Test Case 2: for CP
//	○	From the CP home page , hover on  menu Item >> click on New & Features
//	○	Count total number of Videos Feeds and count the videos feeds those are present in the page >= 3d
	
	//locators CP home page
	
	private By shopMenu = By.xpath("//ul[@role='menubar']//span[contains(text(),'Shop')]");
	private By mensOption = By.xpath("//ul[@role='menubar']//a[contains(text(),\"Men's\")]");
	private By acceptCookiesButton = By.xpath("//button[text()='I Accept']");
	private By closePresalewindow = By.xpath("//div[text()='x']");
	
	private By newAndFeaturesOption = By.xpath("//*[@role='menubar']//a[@title='News & Features']");
	private By threedotsMenu = By.xpath("//*[@class='menu-item']//*[@aria-label=\"Nav Item Button\"]"); // Example locator for video feeds, adjust as needed
	
	
	public void navigateToCPHomepage() {
		navigateTo("CPurl");
		// Handle pre-sale pop-up if present
		try {
			if (isDisplayed(closePresalewindow)) {
				clickElement(closePresalewindow);
				System.out.println("Pre-sale pop-up closed.");
			}
		} catch (Exception e) {
			System.out.println("Pre-sale pop-up not found or already closed.");
		}
		
		// Handle cookie consent if present
				try {
					if (isDisplayed(acceptCookiesButton)) {
						clickElement(acceptCookiesButton);
						System.out.println("Cookie consent accepted.");
					}
				} catch (Exception e) {
					System.out.println("Cookie consent not found or already accepted.");
				}
		
		
	}
	
	public String navigateToShopMenuAndSelectCategory(String category) {
		if (category == null) {
			throw new IllegalArgumentException("category must not be null");
		}
		String cpHomepageWindowHandle=getParentWindowHandle(); // Store the original window handle before clicking the category
		System.out.println("Original window handle stored: " + cpHomepageWindowHandle);
		String key = category.toLowerCase().replace("'", "").trim();
		switch (key) {
			case "mens":
				mouseHoverAndClick(shopMenu, mensOption);
				break;
			case "news":
				mouseHoverAndClick(threedotsMenu, newAndFeaturesOption);
				break;
			default:
				throw new IllegalArgumentException("Unknown category: " + category);
		}
		
		return cpHomepageWindowHandle; // Return the original window handle for later use
	}
	
	public void movetoselectedcategoryWindow(String cpHomepageWindowHandle) {
		// Switch to the new window opened after clicking the category, if one exists
		if (driver.getWindowHandles().size() > 1) {
			getChildWindowHandleAndSwitch(cpHomepageWindowHandle);
		}
}
	
	public void traverseThroughAllPaginatedPages() {
		System.out.print("Traversing through all paginated pages...");
		// Implement logic to click through pagination controls and collect data from each page
		// This may involve clicking "Next" buttons or page number links until no more pages are available
	}
	
	
	
	
	

}
