package pageobjects.employee;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utilities.Constants;
import utilities.SeleniumHelpers;

public class SearchEmployeePage {
	WebDriver driver;
	SeleniumHelpers selenium;

	public SearchEmployeePage(WebDriver driver) {
		this.driver = driver;
		selenium = new SeleniumHelpers(driver);
		// This initElements method will create all WebElements
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	@FindBy(xpath="//td[@class='left']//a")
	private WebElement employeeResults;
	
	//Search Employee
	public String getEmployeeResults() {
		return employeeResults.getText();
	}

}
