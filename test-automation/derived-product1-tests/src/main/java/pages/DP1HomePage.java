package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePageClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DP1HomePage extends BasePageClass {

	public DP1HomePage(WebDriver driver) {
		super(driver);
		
	}

	private By acceptCookiesButton = By.xpath("//button[text()='I Accept']");
	private By closePresalewindow = By.xpath("//div[text()='x']");

	// locators - these are sample locators and may need adjustment for real page
	// DOM
	
	private final By slidesContainerLocator = By.xpath("//*[@data-testid='tile-hero-stories']//button[@data-testid='content-hero-navigation-button']");
	private final By slideTitleLocator = By.xpath("//*[@data-testid='tile-hero-stories']//button[@data-testid='content-hero-navigation-button']//div[contains(@class,'Title')]");
	private final By slideButtonLocator = By.xpath("//*[@data-testid='tile-hero-stories']//button[@data-testid='content-hero-navigation-button']");

	public void navigateToDP1Homepage() {
		navigateTo("DP1url");
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

	public void getTheCountofTicketMenuSlides() {
		
	System.out.println("Inside getTheCountofTicketMenuSlides method");
			List<WebElement> noOfSlides = findElements(slidesContainerLocator);
			int slidesCount=noOfSlides.size();
			System.out.println("------------- " );
		    System.out.println("---------Number of slides present in ---- " + slidesCount);
		    System.out.println("------------- " );
			
			
		
	}


    public void getTheTitleAndDurationOfEachSlide() {
		Map<String, Integer> slideInfo = new LinkedHashMap<>();
		slideInfo = getStoryDurations();
		
		System.out.println("------------- " );
		for (Map.Entry<String, Integer> entry : slideInfo.entrySet()) {
		    System.out.println("Title: " + entry.getKey() + " | Duration: " + entry.getValue() + " seconds");
		}
		System.out.println("------------- " );
	}
    
    
	public List<String> getAllSlideTitles() {
		List<String> titles = new ArrayList<>();
		List<WebElement> slides = findElements(slideTitleLocator);
		for (WebElement s : slides) {
			try {
				
				titles.add(s.getText());
			} catch (Exception e) {
				titles.add("No Title Found");
			}
		}
		return titles;
	}

	public Map<String, Integer> getStoryDurations() {
	    List<WebElement> stories = findElements(slideButtonLocator);
	    Map<String, Integer> storyDurations = new LinkedHashMap<>();
	    
	    System.out.println("Total stories: " + stories.size());

	    //pass the time of any active story
	   By WaitForActiveStory =
	            By.xpath("//button[@data-testid='content-hero-navigation-button' and @aria-selected='true']");
	       
	    boolean isUpdated=CheckForAttributeUpdate("aria-selected", "false", WaitForActiveStory);
	    
	    for (int i = 0; i < stories.size(); i++) {
	        // Find the currently active story
	        By activeStory = 
	            By.xpath("//button[@data-testid='content-hero-navigation-button' and @aria-selected='true']");

	        String title = findElement(By.xpath("//button[@data-testid='content-hero-navigation-button' and @aria-selected='true']//div[contains(@class,'Title')]")).getText();
	        System.out.println("Playing: " + title);

	        long start = System.currentTimeMillis();

	        // Wait until this story is no longer active
	        boolean isUpdated1=CheckForAttributeUpdate("aria-selected", "false", activeStory);

	        long end = System.currentTimeMillis();
	        long duration = (end - start) / 1000;
            storyDurations.put(title, (int) duration);
	        System.out.println("Duration of '" + title + "': " + duration + " seconds");
	    }
	    return storyDurations;
	}



}
