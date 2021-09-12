package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.SoftAssert;

public class WebDriverManager {

	protected WebDriver driver;
	protected SeleniumHelpers selenium;
	protected SoftAssert sfassert;
	protected JavaHelpers javahelpers;
	private String chromedriver_path = "utilities/drivers/chromedriver.exe";
	private String iedriver_path = "utilities/drivers/IEDriverServer.exe";
	private String geckodriver_path = "utilities/drivers/geckodriver.exe";
	private ChromeOptions chrome_options = new ChromeOptions();

	public WebDriver setUp(String node, String browserName) throws Exception {
		browserName = browserName.toLowerCase();

		// Local machine run
		if (node.equalsIgnoreCase("local")) {
			switch (browserName) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", geckodriver_path);
				FirefoxOptions options = new FirefoxOptions();
				options.setAcceptInsecureCerts(true);
				driver = new FirefoxDriver(options);
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver", chromedriver_path);
				chrome_options.addArguments("--incognito");
				driver = new ChromeDriver(chrome_options);
				break;

			case "chrome-headless":
				System.setProperty("webdriver.chrome.driver", chromedriver_path);
				chrome_options.addArguments("headless");
				chrome_options.addArguments("window-size=1920x1080");
				driver = new ChromeDriver(chrome_options);
				break;

			case "ie":
				System.setProperty("webdriver.ie.driver", iedriver_path);
				driver = new InternetExplorerDriver();
				break;

			default:
				System.setProperty("webdriver.chrome.driver", chromedriver_path);
				driver = new ChromeDriver();
				break;
			}

		}

		// Docker Run.
		else {
			// Docker Parallel code execution goes here.

		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public void tearDown(WebDriver driver) throws Exception {
		driver.quit();
	}
}
