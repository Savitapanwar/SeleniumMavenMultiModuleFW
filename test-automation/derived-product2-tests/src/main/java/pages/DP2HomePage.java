package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePageClass;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DP2HomePage extends BasePageClass {

	public DP2HomePage(WebDriver driver) {
		super(driver);
		
	}

	private By acceptCookiesButton = By.xpath("//button[text()='I Accept']");
	private By closePresalewindow = By.xpath("//div[text()='x']");


	
	public void navigateToDP1Homepage() {
		navigateTo("DP2url");
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

	public List<String> getAllFooterLinks() {
		 // Scroll to footer --Not required
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Get all links in footer
        List<WebElement> links = driver.findElements(By.xpath("//footer//a[@href]"));

        // Extract hrefs
        List<String> hrefs = new ArrayList<>();
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                hrefs.add(href);
            }
        }

        return hrefs;
	}
    
	
	public Map<String, Set<String>> verifyDuplicateLinksInFooter(List<String> hrefs) {
		// Detect duplicates
        Set<String> uniqueLinks = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        Map<String, Set<String>> hrefInfo = new LinkedHashMap<>();
        for (String href : hrefs) {
            if (!uniqueLinks.add(href)) {
            	System.out.println("-----------Duplicate link found: " + href);
                duplicates.add(href);
            }
        }

        System.out.println("Total links: " + hrefs.size());
        System.out.println("Unique links: " + uniqueLinks.size());
        System.out.println("Duplicates: " + duplicates.size() + " - " + duplicates);
        
        hrefInfo.put("Unique", uniqueLinks);
        hrefInfo.put("Duplicate", duplicates);
        
        return hrefInfo;
	}

	/*
	 * This method takes a set of unique hrefs and a file name, and writes the hrefs to a CSV file with the specified name.
	*/
	public void writeFooterLinksToCSV(Set<String> hrefs,String fileName) {
		// Save to CSV
		try (FileWriter writer = new FileWriter(fileName)) {
			writer.write("Link\n");
			for (String href : hrefs) {
				writer.write(href + "\n");
			}
		} catch (IOException e) {
			System.out.println("Error writing links to CSV: " + e.getMessage());
			Assert.fail("Footers Links are missing");
		}
	}


	
	
	
	
	
	
	
	
	
}
