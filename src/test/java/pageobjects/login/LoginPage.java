package pageobjects.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import io.qameta.allure.Step;
import utilities.Constants;
import utilities.SeleniumHelpers;

public class LoginPage {
	WebDriver driver;
	SeleniumHelpers selenium;

	public LoginPage(WebDriver driver) {
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

	@FindBy(id = "txtUsername")
	private WebElement txtUserName;

	@FindBy(id = "txtPassword")
	private WebElement txtPassword;

	@FindBy(id = "btnLogin")
	private WebElement btnLogin;

	@FindBy(id = "spanMessage")
	private WebElement invalidCrendialsMsg;

	@FindBy(id = "menu_dashboard_index")
	private WebElement dashboardIndex;

	@Step("Enter User Name {0}")
	public void enterUserName(String username) {
		txtUserName.clear();
		txtUserName.sendKeys(username);
	}

	@Step("Enter Password {0}")
	public void enterPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);
	}

	@Step("Enter UserNand {0} and Password {1}")
	public void loginToOpenHRM(String username,String password) throws InterruptedException {
		enterUserName(username);
		enterPassword(password);
		clickOnLogin();
	}

	@Step("Click on login")
	public void clickOnLogin() throws InterruptedException {
		btnLogin.click();

	}

	@Step("Ensure Dashboard page display")
	public void getDashboardDetails() {
		selenium.waitTillElementIsVisible(dashboardIndex);
	}

	@Step("Check Invalid login message")
	public void getInvalidLoginMsgDetails() {
		invalidCrendialsMsg.isDisplayed();
	}

}
