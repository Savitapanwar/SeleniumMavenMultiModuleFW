package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePageClass;
import io.qameta.allure.Allure;

public class CPNews extends BasePageClass {

	public CPNews(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	By videoFeeds = By.xpath("//*[text()='VIDEOS']/..//a[@data-testid='tile-article-link']"); // Example locator for video feeds, adjust as needed
	By videoFeedDimention = By.xpath("//*[text()='VIDEOS']/..//a[@data-testid='tile-article-link']/parent::div//span"); // Example locator for video feed date, adjust as needed
	
	
	public void countVideoFeedsGreaterThan3D() {
		int totalVideoFeeds = findElements(videoFeeds).size();
		System.out.println("Total number of video feeds: " + totalVideoFeeds);
		Allure.step("Total number of video feeds: " + totalVideoFeeds);
		
		int videoFeedsGreaterThan3D = 0;
		for (int i = 0; i < totalVideoFeeds; i++) {
			String dataText = findElements(videoFeedDimention).get(i).getText().toLowerCase().replace("d", "");
			int dimension = Integer.parseInt(dataText);
			if (dimension>3) {
				videoFeedsGreaterThan3D++;
			}
		}
		
		System.out.println("Number of video feeds greater than 3d: " + videoFeedsGreaterThan3D);
		Allure.step("Number of video feeds greater than 3d: " + videoFeedsGreaterThan3D);
	}
	
}
