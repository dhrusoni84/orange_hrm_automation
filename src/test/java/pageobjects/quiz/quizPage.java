package pageobjects.quiz;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utilities.Constants;
import utilities.SeleniumHelpers;

public class quizPage {
	
	WebDriver driver;
	SeleniumHelpers selenium;
	public quizPage(WebDriver driver)
	{
		this.driver = driver;
		selenium = new SeleniumHelpers(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}

	@FindBy(id = "txtUsername")
	private WebElement txtUserName;
}
