package tests.employee;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import pageobjects.dashboard.DashboardPage;
import pageobjects.employee.EmployeePage;
import pageobjects.employee.SearchEmployeePage;
import pageobjects.login.LoginPage;
import utilities.Constants;
import utilities.JavaHelpers;
import utilities.SeleniumHelpers;
import utilities.TestSetup;
import utilities.WebDriverManager;

public class EmployeeTest extends TestSetup {

	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private EmployeePage employeePage;
	private WebDriverManager driverManager;
	private SearchEmployeePage searchEmployeePage;
	private String employeePhotograph = "test-input/Resources/userProfile.png";
	private String invalidExtensionPhotograph = "test-input/Resources/invalidPhotograph.txt";
	private String invalidFilePhotographSize = "test-input/Resources/1_mbFile.jpg";
	Faker faker = new Faker();
	private String firstName = faker.name().firstName();
	private String lastName = faker.name().lastName();
	private String employeeID;
	
	//Test Data
	private String propertyFile = "test-input/login/login.properties";
	private String adminUserName = JavaHelpers.getPropertyValue(propertyFile, "userName_"+Constants.Env);
	private String adminPassword = JavaHelpers.getPropertyValue(propertyFile, "password_"+Constants.Env);
	private String siteURL=JavaHelpers.getPropertyValue(propertyFile, "OrangeHRM_"+Constants.Env);

	@BeforeClass
	@Parameters({ "node", "browser" })
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception  {
		driverManager = new WebDriverManager();
		driver = driverManager.setUp(node, browser);
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		employeePage = new EmployeePage(driver);
		searchEmployeePage = new SearchEmployeePage(driver);
		selenium = new SeleniumHelpers(driver);
		selenium.navigateToPage(siteURL);
	}

	@Epic("Employee Management feature")
	@Description("Verify Employee functionality")
	@Story("Add Employee details")
	@Test(priority = 1)
	public void verifyByAddingUserDetails() throws InterruptedException {
		loginPage.loginToOpenHRM(adminUserName,adminPassword);
		dashboardPage.clickOnEmployeeMenu();
		employeePage.clickOnAddEmployee();
		employeePage.addEmployeeDetails(firstName, "Testing", lastName);
		employeePage.addEmployeePhotograph(employeePhotograph);
		employeePage.saveEmployeeDetails();
		employeeID = employeePage.getEmployeeID();
		sfassert.assertEquals(employeePage.getEmpFirstName(), firstName,
				"Employee firstname Details not saved properly");
		sfassert.assertEquals(employeePage.getEmpMiddleName(), "Testing",
				"Employee middle name Details not saved properly");
		sfassert.assertEquals(employeePage.getEmpLastName(), lastName, "Employee last name Details not saved properly");
		sfassert.assertTrue(employeePage.isEmployeePicDisplayed(), "Employee Profile pic not update");
		sfassert.assertAll();
	}

	@Epic("Employee Management feature")
	@Description("Verify by uploading invalid extenstion document")
	@Story("Invalid extension not supported")
	@Test(priority = 2)
	public void verifyByUploadingInvalidExtensionPhotograph() {
		dashboardPage.clickOnEmployeeMenu();
		employeePage.clickOnAddEmployee();
		employeePage.addEmployeeDetails(firstName, "Testing", lastName);
		employeePage.addEmployeePhotograph(invalidExtensionPhotograph);
		employeePage.saveEmployeeDetails();
		sfassert.assertTrue(employeePage.isFlashWarningMessageDisplayed(),
				"Warning message not appear user is able to uplpod restricted extension files");
		sfassert.assertAll();
	}

	@Epic("Employee Management feature")
	@Description("Verify by uploading More than 1 MB File")
	@Story("Profile page size not expcted more than 1 MB")
	@Test(priority = 3)
	public void verifyUploadingMoreThan1MbFile() {
		dashboardPage.clickOnEmployeeMenu();
		employeePage.clickOnAddEmployee();
		employeePage.addEmployeeDetails(firstName, "Testing", lastName);
		employeePage.addEmployeePhotograph(invalidFilePhotographSize);
		employeePage.saveEmployeeDetails();
		sfassert.assertTrue(employeePage.isFlashWarningMessageDisplayed(),
				"Warning message not appear user is able to uplpod more than 1 mb file");
		sfassert.assertAll();
	}

	@Epic("Employee Management feature")
	@Description("Verify by searching employee")
	@Story("Search existing employee")
	@Test(priority = 4, dependsOnMethods = { "verifyByAddingUserDetails" })
	public void verifyBySearchingExistingEmployee() {
		dashboardPage.clickOnEmployeeMenu();
		employeePage.clickOnEmployeeList();
		employeePage.enterEmployeeDeails(employeeID);
		employeePage.searchEmployee();
		sfassert.assertTrue(searchEmployeePage.getEmployeeResults().contains(employeeID), "Search result not display");
		sfassert.assertAll();
	}

	@AfterClass
	public void tearDown() throws Exception {
		driverManager.tearDown(driver);
	}

}