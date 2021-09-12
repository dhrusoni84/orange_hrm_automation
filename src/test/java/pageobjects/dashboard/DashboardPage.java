package pageobjects.dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utilities.Constants;
import utilities.SeleniumHelpers;

public class DashboardPage {
	WebDriver driver;
	SeleniumHelpers selenium;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		selenium = new SeleniumHelpers(driver);
		// This initElements method will create all WebElements
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}

	@FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
	private WebElement dashboardHeader;

	@FindBy(id = "menu_pim_viewPimModule")
	private WebElement employeeMenu;

	public String getDashboardHeaderDetails() {
		return dashboardHeader.getText();
	}

	public void clickOnEmployeeMenu() {
		employeeMenu.click();
	}

}
