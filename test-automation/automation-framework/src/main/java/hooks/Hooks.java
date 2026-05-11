package hooks;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

// Optional Allure import; if Allure isn't on the classpath this block will be caught at runtime
import io.qameta.allure.Allure;

public class Hooks {

	@Before
	public void setUp() {
		DriverFactory.initDriver(ConfigReader.getProperty("browser"));
	}

	@After
	public void tearDown(Scenario scenario) {
		WebDriver driver = DriverFactory.getDriver();

		if (scenario != null && scenario.isFailed() && driver != null) {
			try {
				// capture screenshot as bytes
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

				// try attaching to Allure (if present)
				try {
					Allure.addAttachment("Failed Screenshot - " + scenario.getName(), new ByteArrayInputStream(screenshot));
				} catch (NoClassDefFoundError | Exception ignore) {
					// Allure not available or attachment failed — ignore and continue to fallback
				}

				// fallback: write screenshot to target/screenshots/<scenario>.png
				try {
					File screenshotsDir = new File("target" + File.separator + "screenshots");
					if (!screenshotsDir.exists()) {
						screenshotsDir.mkdirs();
					}
					String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9.-]", "_");
					File out = new File(screenshotsDir, safeName + ".png");
					try (FileOutputStream fos = new FileOutputStream(out)) {
						fos.write(screenshot);
					}
				} catch (IOException e) {
					System.err.println("Failed to write screenshot file: " + e.getMessage());
				}

			} catch (Exception e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
		}

		DriverFactory.quitDriver();
	}
}
