package pageobjects.employee;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utilities.Constants;
import utilities.SeleniumHelpers;

public class EmployeePage {
	WebDriver driver;
	SeleniumHelpers selenium;


	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		selenium = new SeleniumHelpers(driver);
		// This initElements method will create all WebElements
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}

	/*
	 * All WebElements are identified by @FindBy annotation
	 * 
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css,
	 * className, xpath as attributes.
	 */

	@FindBy(id = "menu_pim_addEmployee")
	private WebElement addEmployeeMenu;
	
	@FindBy(id="menu_pim_viewEmployeeList")
	private WebElement employeeListMenu;

	@FindBy(id = "firstName")
	private WebElement employeeFirstName;

	@FindBy(id = "middleName")
	private WebElement employeeMiddleName;

	@FindBy(id = "lastName")
	private WebElement employeeLastName;

	@FindBy(id = "photofile")
	private WebElement employeePhotograph;

	@FindBy(id = "btnSave")
	private WebElement employeeSaveBtn;

	// Saved Employee details data
	@FindBy(id = "personal_txtEmpFirstName")
	private WebElement firstName;

	@FindBy(id = "personal_txtEmpMiddleName")
	private WebElement middleName;

	@FindBy(id = "personal_txtEmpLastName")
	private WebElement lastName;

	@FindBy(id="personal_txtEmployeeId")
	private WebElement employeeID;
	
	@FindBy(id = "empPic")
	private WebElement empPic;

	@FindBy(xpath = "//div[@class='message warning fadable']")
	private WebElement flashWarningMessage;
	
	
	@FindBy(id="empsearch_id")
	private WebElement searchEmployee;
	
	@FindBy(xpath="//div[@class='ac_results']")
	private WebElement searchResults;
	
	@FindBy(xpath="//li[@class='ac_even ac_over']//strong")
	private WebElement clickOnSearchResult;
	
	@FindBy(id="searchBtn")
	private WebElement searchBtn;
	

	public void clickOnAddEmployee() {
		addEmployeeMenu.click();
	}
	
	public void clickOnEmployeeList() {
		employeeListMenu.click();
	}

	public void addEmployeeDetails(String firstname, String middlename, String lastname) {
		employeeFirstName.clear();
		employeeFirstName.sendKeys(firstname);
		employeeMiddleName.clear();
		employeeMiddleName.sendKeys(middlename);
		employeeLastName.clear();
		employeeLastName.sendKeys(lastname);
	}

	public void addEmployeePhotograph(String uploadPhotograph) {
		File employeePhoto = new File(uploadPhotograph);
		employeePhotograph.sendKeys(employeePhoto.getAbsolutePath());
	}

	public void saveEmployeeDetails() {
		employeeSaveBtn.click();
	}

	// Check Save employee details.

	public String getEmpFirstName() {
		return firstName.getAttribute("value");
	}

	public String getEmpMiddleName() {
		return middleName.getAttribute("value");
	}

	public String getEmpLastName() {
		return lastName.getAttribute("value");
	}
	

	public String getEmployeeID() {
		return employeeID.getAttribute("value");
	}

	public boolean isEmployeePicDisplayed() {
		return empPic.isDisplayed();
	}

	public boolean isFlashWarningMessageDisplayed() {
		selenium.waitTillElementIsVisible(flashWarningMessage);
		return flashWarningMessage.isDisplayed();
	}
	
	public void enterEmployeeDeails(String employeeDetails) {
		searchEmployee.clear();
		searchEmployee.sendKeys(employeeDetails);
	}
	
	public void searchEmployee()
	{
		searchBtn.click();
	}
	
}