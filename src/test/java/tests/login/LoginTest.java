package tests.login;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import pageobjects.dashboard.DashboardPage;
import pageobjects.login.LoginPage;
import utilities.Constants;
import utilities.JavaHelpers;
import utilities.SeleniumHelpers;
import utilities.TestSetup;
import utilities.WebDriverManager;

public class LoginTest extends TestSetup {
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private WebDriverManager driverManager;
	
	//Test Data
	private String propertyFile = "test-input/login/login.properties";
	private String adminUserName = JavaHelpers.getPropertyValue(propertyFile, "userName_"+Constants.Env);
	private String adminPassword = JavaHelpers.getPropertyValue(propertyFile, "password_"+Constants.Env);
	private String adminInvalidPassword = JavaHelpers.getPropertyValue(propertyFile, "invalidPassword_"+Constants.Env);
	private String siteURL=JavaHelpers.getPropertyValue(propertyFile, "OrangeHRM_"+Constants.Env);

	@BeforeClass
	@Parameters({ "node", "browser" })
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception  {
		driverManager = new WebDriverManager();
		driver = driverManager.setUp(node, browser);
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		selenium = new SeleniumHelpers(driver);
		selenium.navigateToPage(siteURL);
	}

	@Epic("Login Feature")
	@Description("Loging Attempt : Invalid credentials")
	@Story("Flash validation message display on invalid credentials")
	@Test(priority = 1)
	public void verifyInvalidLoginAttempt() throws InterruptedException {
		loginPage.enterUserName(adminUserName);
		loginPage.enterPassword(adminInvalidPassword);
		loginPage.clickOnLogin();
		loginPage.getInvalidLoginMsgDetails();
	}

	@Epic("Login Feature")
	@Description("Login Attempt : Valid credentials")
	@Story("Successfull login with valid credentials")
	@Test(priority = 2)
	public void verifyValidLoginAttempt() throws InterruptedException {
		loginPage.enterUserName(adminUserName);
		loginPage.enterPassword(adminPassword);
		loginPage.clickOnLogin();
		loginPage.getDashboardDetails();
		sfassert.assertEquals("Dashboard", dashboardPage.getDashboardHeaderDetails(), "Dashboard header missing");
		sfassert.assertAll();
	}

	@AfterClass
	public void tearDown() throws Exception {
		driverManager.tearDown(driver);
	}

}
