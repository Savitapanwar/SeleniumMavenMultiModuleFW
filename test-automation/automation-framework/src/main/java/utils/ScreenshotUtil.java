package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static byte[] captureScreenshot(WebDriver driver) {

		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public static String captureScreenshotToFile(WebDriver driver, String screenshotName) {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";

		try {

			Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/"));

			Files.copy(srcFile.toPath(), Paths.get(path));

		} catch (IOException e) {

			e.printStackTrace();
		}

		return path;
	}

}
