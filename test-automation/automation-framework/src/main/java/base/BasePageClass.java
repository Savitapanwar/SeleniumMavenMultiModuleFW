package base;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import org.testng.Assert;
import utils.ConfigReader;
import utils.ScreenshotUtil;
import utils.WaitUtil;

public class BasePageClass {
	//BasePageClass will be the parent class for all page classes. It will contain common methods and properties that can be used across different pages.

	//Protected WebDriver instance to be used by child classes
	protected WebDriver driver;

	//Constructor to initialize the WebDriver instance
	public BasePageClass(WebDriver driver) {
		this.driver = driver;
	}
	
	//Common methods for interacting with web elements can be added here. For example:
	
	public WebElement findElement(By locator) {
		return WaitUtil.waitForPresence(driver, locator);
	}
	
	public List<WebElement> findElements(By locator) {
		return WaitUtil.waitForAllElementsVisibility(driver, locator);
	}
	public void navigateTo(String url) {

		driver.get(ConfigReader.getProperty(url));

	}
	
	public void clickElement(By locator) {
			WaitUtil.waitForClickable(driver, locator).click();


	}

	public void enterText(By locator, String text) {

			findElement(locator).sendKeys(text);

	}

	public String getElementText(By locator) {

			return findElement(locator).getText();

	}
	
	public void mouseHover(By locator) {
		try {
			// Implement mouse hover action using Actions class
			 Actions actions = new Actions(driver);
			 actions.moveToElement(driver.findElement(locator)).perform();
		} catch (Exception e) {
			System.out.println("Error performing mouse hover: " + e.getMessage());
		}
	}
	
	public void mouseHoverAndClick(By hoverLocator, By clickLocator) {
		try {
			WebElement hoverElement = WaitUtil.waitForVisibility(driver, hoverLocator);
			Actions actions = new Actions(driver);
			// Hover on main menu
			actions.moveToElement(hoverElement).perform();
			
			// Wait for sub menu to appear and then click on it
			WebElement clickElement = WaitUtil.waitForClickable(driver, clickLocator);
			
			// Hover on sub menu and click
			actions.moveToElement(clickElement).click().build().perform();
		} catch (Exception e) {
			Assert.fail("Mouse hover failed" + hoverLocator+ clickLocator);
		}
	}


	public void mouseHoverAndClickSameTab(By hoverLocator, By clickLocator) {
		try {
			WebElement hoverElement = WaitUtil.waitForVisibility(driver, hoverLocator);
			Actions actions = new Actions(driver);
			actions.moveToElement(hoverElement).perform();
			
			WebElement clickElement = WaitUtil.waitForClickable(driver, clickLocator);
			String href = clickElement.getAttribute("href");
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('target');", clickElement);
			}
			if (href != null && !href.isEmpty()) {
				driver.get(href);
			} else {
				actions.moveToElement(clickElement).click().build().perform();
			}
		} catch (Exception e) {
			System.out.println("Error performing mouse hover and same-tab click: " + e.getMessage());
		}
	}
	
	public String getParentWindowHandle() {
		try {
			return driver.getWindowHandle();
		} catch (Exception e) {
			System.out.println("Error getting parent window handle: " + e.getMessage());
			return null;
		}
	}
	
	public void getChildWindowHandleAndSwitch(String parentWindowHandle) {
		try {
			long startTime = System.currentTimeMillis();
			while (driver.getWindowHandles().size() <= 1 && System.currentTimeMillis() - startTime < 10000) {
				Thread.sleep(200);
			}
			for (String handle : driver.getWindowHandles()) {
				if (!handle.equals(parentWindowHandle)) {
					driver.switchTo().window(handle);
					System.out.println("Switched to child window with handle: " + handle);
					try {
						WaitUtil.waitForPageLoad(driver);
					} catch (Exception e) {
						System.out.println("Warning: waiting for page load in child window failed: " + e.getMessage());
					}
					return;
				}
			}
			System.out.println("No child window handle found after waiting for new tab/window.");
		} catch (Exception e) {
			System.out.println("Error switching to child window: " + e.getMessage());
		}
	}
	
	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("Error checking if element is displayed: " + e.getMessage());
			return false;
		}
	}
	
	public void storeDetailsInTextFile(String title, String price, String topSeller) {
		// This method will contain the logic to store the extracted details in a text file
		// We can use Java's FileWriter or BufferedWriter to write the details to a text file
		try (FileWriter writer = new FileWriter("jacket_details.txt", true)) { // true for append mode
			writer.write("Title: " + title + "\n");
			writer.write("Price: " + price + "\n");
			if (!topSeller.isEmpty()) {
				writer.write("Top Seller Message: " + topSeller + "\n");
			}
			writer.write("--------------------------------------------------\n");
		} catch (IOException e) {
			System.out.println("Error writing details to text file: " + e.getMessage());
		}
	}
	
	public void extractDetailsFromCurrentPage(By productDataContainer, By productTitle, By productPrice, By topSellerMessage) {
		
		List<WebElement> productGridElement = driver.findElements(productDataContainer); // Get all product containers on current page
		 //div[@class='product-card row']//div[@class='columns small-12 medium-12'][2]//div[@class='product-card-title']
		   for (int i = 1; i <= productGridElement.size(); i++) {
			   String title = driver.findElement(By.xpath("(" + productTitle + ")[" + i + "]")).getText();
			   if (title.contains("Jacket")) {
				   String price = driver.findElement(By.xpath("(" + productPrice + ")[" + i + "]")).getText();
				   String topSeller = "";
				   try {
					   topSeller = driver.findElement(By.xpath("(" + topSellerMessage + ")[" + i + "]")).getText();
				   } catch (Exception e) {
					   // Top seller message not present, we can ignore this exception
				   }
				   // Now we have the title, price, and top seller message (if available) for this jacket
				   // We can store these details in a text file
				   storeDetailsInTextFile(title, price, topSeller);
			   }
		   }
		
	}
	
	public boolean CheckForAttributeUpdate(String attributeName, String expectedValue, By locator) {
		return WaitUtil.waitForAttributeToUpdate(driver, findElement(locator), attributeName, expectedValue);
	}
	
    public void verifyPageTitle(String expectedTitle) {
		boolean IsMatching = WaitUtil.waitForTitleContains(driver, expectedTitle);
		if(IsMatching) {
			System.out.println("Page title matches expected value: " + expectedTitle);
		} else {
			Assert.assertEquals(IsMatching, "Page title does not match expected value.");
		}
		
	}

}
