package utilities;

import java.io.IOException;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;


public class TestSetup {
	private JavaHelpers javahelp = new JavaHelpers();
	protected WebDriver driver;
	protected WebDriver driverForNewWindow;
	protected SeleniumHelpers seleniumForNewWindow;
	protected SeleniumHelpers selenium;
	protected SoftAssert sfassert;
	private WebDriverManager driverManagerLocal;
	private WebDriver driverLocal;
	private SeleniumHelpers seleniumLocal;
	
	
	@BeforeSuite (alwaysRun = true)
	public void beforeSuite() throws Exception
	{	
		driverManagerLocal = new WebDriverManager();
		try {
			javahelp.DeleteAllFilesFromFolder(Constants.SCREENSHOT_LOCATION);
			seleniumLocal = new SeleniumHelpers(driverLocal); 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{	 
		 //capturing screenshot if failed
		 if(ITestResult.FAILURE==result.getStatus())
		 {
			 selenium.takeScreenshot(result.getName());
			 
			 //Only if new window opened
			 if(driverForNewWindow!=null)
			 {
				 try 
				 {
					 seleniumForNewWindow.takeScreenshot(result.getName());
				 }
				 catch (NoSuchSessionException e )
				 {
					 //Session not present.
				 }
			 }
		 }
	}
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException, IOException
	{
		//Initializing soft assert variable
		sfassert = new SoftAssert();
	}
}
