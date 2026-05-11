package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    // ThreadLocal ensures each thread has its own driver instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String browser) {
        WebDriver webDriver = null;

        switch (browser.toLowerCase()) {
            case "chrome":
            	ChromeOptions options = new ChromeOptions();
            	options.addArguments("--remote-allow-origins=*");
            	options.addArguments("--disable-notifications");
            	options.addArguments("--disable-popup-blocking");
            	options.addArguments("--disable-infobars");
            	options.addArguments("--disable-blink-features=AutomationControlled");
            	options.addArguments("--disable-dev-shm-usage");
            	options.addArguments("--no-sandbox");
            	options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            	options.setExperimentalOption("useAutomationExtension", false);
            	try {
            		Path userDataDir = Files.createTempDirectory("chrome-profile-");
            		options.addArguments("user-data-dir=" + userDataDir.toAbsolutePath().toString());
            	} catch (IOException e) {
            		throw new RuntimeException("Unable to create Chrome user-data-dir for parallel execution", e);
            	}
            	webDriver = new ChromeDriver(options);
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "edge":
            	System.setProperty("webdriver.edge.driver", "C:/Drivers/msedgedriver.exe");
            	//WebDriverManager.edgedriver().setup();
                webDriver= new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
        	System.out.println("Quitting driver for thread: " + Thread.currentThread().getName());
            driver.get().quit();
            driver.remove(); // clean up ThreadLocal
        }
    }
}
