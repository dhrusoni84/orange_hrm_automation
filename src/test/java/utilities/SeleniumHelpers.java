package utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


public class SeleniumHelpers {
	WebDriver driver;
	JavaHelpers helper;

	public SeleniumHelpers(WebDriver driver) {
		this.driver = driver;
		helper = new JavaHelpers();
	}

	// Navigation
	public void navigateToPage(String url) throws InterruptedException {
		try {
			driver.get(url);
		} catch (Exception e) {
			// If any timeout exception, loading page again.
			driver.get(url);
		}
	}

	// Hard refresh
	public void refreshPage() throws InterruptedException {
		driver.navigate().refresh();
		hardWait(4);
	}

	public void hardWait(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}

	public WebElement waitTillElementIsVisible(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		wait.until(ExpectedConditions.visibilityOf(e));
		return e;
	}

	// Take screenshots
	public void takeScreenshot(String fileName) throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile, new File(Constants.SCREENSHOT_LOCATION));
	}

}
