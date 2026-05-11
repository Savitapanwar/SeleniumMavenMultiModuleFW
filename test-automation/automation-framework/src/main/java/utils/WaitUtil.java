package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

	private static final int DEFAULT_TIMEOUT = 20;
	private static final int DEFAULT_POLLING = 2;
	// Utilities are static and receive WebDriver per-call. Avoid storing WebDriver/Wait as static state
	// to prevent stale or uninitialized driver issues.
	/**
	 * Private constructor
	 * Prevents object creation
	 */
	private WaitUtil() {
		// private constructor to prevent instantiation
	}

	/**
	 * Static factory method to create WaitUtil instance
	 */

	/**
	 * Wait for visibility of element
	 */
	public static WebElement waitForVisibility(WebDriver driver,
			By locator) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Element not visible: " + locator, e);
		}
	}

	/**
	 * Wait for visibility of WebElement
	 */
	public static WebElement waitForVisibility(WebDriver driver,
			WebElement element) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.visibilityOf(element));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Element not visible", e);
		}
	}

	/**
	 * Wait for element to be clickable
	 */
	public static WebElement waitForClickable(WebDriver driver,
			By locator) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Element not clickable: " + locator, e);
		}
	}

	/**
	 * Wait for presence of element
	 */
	public static WebElement waitForPresence(WebDriver driver,
			By locator) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Element not present in DOM: " + locator, e);
		}
	}

	/**
	 * Wait for all elements visibility
	 */
	public static List<WebElement> waitForAllElementsVisibility(
			WebDriver driver,
			By locator) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Elements not visible: " + locator, e);
		}
	}

	/**
	 * Wait for invisibility of element
	 */
	public static boolean waitForInvisibility(WebDriver driver,
			By locator) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Element still visible: " + locator, e);
		}
	}

	/**
	 * Wait for text in element
	 */
	public static boolean waitForTextToBePresent(WebDriver driver,
			By locator,
			String text) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Text not present: " + text, e);
		}
	}

	/**
	 * Wait for title contains
	 */
	public static boolean waitForTitleContains(WebDriver driver,
			String title) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.titleContains(title));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Title does not contain: " + title, e);
		}
	}

	/**
	 * Wait for exact title
	 */
	public static boolean waitForTitleIs(WebDriver driver,
			String title) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.titleIs(title));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Title does not match: " + title, e);
		}
	}

	/**
	 * Wait for URL contains
	 */
	public static boolean waitForUrlContains(WebDriver driver,
			String partialUrl) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.urlContains(partialUrl));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"URL does not contain: " + partialUrl, e);
		}
	}

	/**
	 * Wait for exact URL
	 */
	public static boolean waitForUrlToBe(WebDriver driver,
			String url) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.urlToBe(url));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"URL does not match: " + url, e);
		}
	}

	/**
	 * Wait for frame and switch
	 */
	public static void waitForFrameAndSwitch(WebDriver driver,
			By locator) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Frame not available: " + locator, e);
		}
	}

	/**
	 * Wait for alert
	 */
	public static Alert waitForAlert(WebDriver driver) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.alertIsPresent());

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Alert not present", e);
		}
	}

	/**
	 * Fluent wait
	 */
	public static WebElement fluentWait(WebDriver driver,
			By locator,
			int timeoutSeconds,
			int pollingSeconds) {

		FluentWait<WebDriver> fluentWait =
				new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeoutSeconds))
				.pollingEvery(Duration.ofSeconds(pollingSeconds))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				
	}

	/**
	 * Wait for page load complete
	 */
	public static void waitForPageLoad(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * Hard wait
	 */
	public static void hardWait(int seconds) {

		try {

			Thread.sleep(seconds * 1000L);

		} catch (InterruptedException e) {

			Thread.currentThread().interrupt();

			throw new RuntimeException(
					"Interrupted during hard wait", e);
		}
	}

	/**
	 * Wait for stale element , wait until the element is no longer attached to the DOM
	 */
	public static boolean waitForStaleElement(WebDriver driver,
			WebElement element) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.stalenessOf(element));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Element did not become stale", e);
		}
	}

	/**
	 * Wait for number of windows
	 */
	public static boolean waitForNumberOfWindows(WebDriver driver,
			int numberOfWindows) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));

		} catch (TimeoutException e) {

			throw new RuntimeException(
					"Expected number of windows not opened", e);
		}
	}

	/**
	 * Custom timeout visibility wait
	 */
	public static WebElement waitForVisibility(WebDriver driver,
			By locator,
			int timeoutInSeconds) {

		WebDriverWait customWait =
				new WebDriverWait(driver,
						Duration.ofSeconds(timeoutInSeconds));

		return customWait.until(
				ExpectedConditions
				.visibilityOfElementLocated(locator));
	}
	
	public static boolean waitForAttributeToUpdate(WebDriver driver, WebElement element, String attribute, String expectedValue) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
	        return wait.until(ExpectedConditions.attributeToBe(element, attribute, expectedValue));
	    } catch (TimeoutException e) {
	        throw new RuntimeException(
	                "Attribute '" + attribute + "' did not update to '" + expectedValue + "'", e);
	    }
	}
}