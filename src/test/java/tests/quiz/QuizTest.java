package tests.quiz;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.quiz.quizPage;
import utilities.Constants;
import utilities.JavaHelpers;
import utilities.SeleniumHelpers;
import utilities.TestSetup;
import utilities.WebDriverManager;

public class QuizTest extends TestSetup {

	private quizPage quizPage;
	private WebDriverManager driverManager;
	private String propertyFile = "test-input/quiz/quiz.properties";
	private String siteURL = JavaHelpers.getPropertyValue(propertyFile, "quiz_" + Constants.Env);
	 
	
	

	@BeforeClass
	@Parameters({ "node", "browser" })
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception {
		driverManager = new WebDriverManager();
		driver = driverManager.setUp(node, browser);
		quizPage = new quizPage(driver);
		selenium = new SeleniumHelpers(driver);
		selenium.navigateToPage(siteURL);
		
	}

	@Test(invocationCount=3)
	public void loginToTheQuizDragAndDrop() throws AWTException, InterruptedException
	{ 
		try {
			  if(driver.findElements(By.xpath("//strong[text()='Matching Drag & Drop - Testing\u00a0']")).size()!=0)
			    {  
			    	System.out.println("Drag and drop display"); 
			    	WebElement fromElement = driver.findElement(By.xpath("//div[text()='Pet']"));
			    	WebElement toElement = driver.findElement(By.xpath("//div[text()='DOG']"));
			    	WebElement clickNextButton = driver.findElement(By.xpath("//a[text()='NEXT']"));
			    	Actions builder = new Actions(driver);
			    	Action dragAndDrop = builder.clickAndHold(fromElement)
			    			.moveToElement(toElement)
			    			.release(toElement)
			    			.build();
			    	dragAndDrop.perform();
			    	
			    	WebElement fromElement1 = driver.findElement(By.xpath("//div[text()='Glass']"));
			    	WebElement toElement1 = driver.findElement(By.xpath("//div[text()='Window']"));
			    	Action dragAndDrop1 = builder.clickAndHold(fromElement1)
			    			.moveToElement(toElement1)
			    			.release(toElement1)
			    			.build();
			    	dragAndDrop1.perform();
			    	
			    	WebElement fromElement2 = driver.findElement(By.xpath("//div[text()='Metal']"));
			    	WebElement toElement2 = driver.findElement(By.xpath("//div[text()='Car']"));
			    	Action dragAndDrop2 = builder.clickAndHold(fromElement2)
			    			.moveToElement(toElement2)
			    			.release(toElement2)
			    			.build();
			    	dragAndDrop2.perform();
			    	clickNextButton.click();
			    	Thread.sleep(1000);
			    	WebElement clickContinueButton = driver.findElement(By.xpath("//a[text()='Continue']"));
			    	clickContinueButton.click();
			    	
			    }
			    else 	
			    {
			    	System.out.println("no quiz question");
			    }
			
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Element is not present, hence not displayed as well");
		}
	       
		try {
		    if (driver.findElements(By.xpath("//p[text()='Fill In The Blank\u00a0']")).size() !=0)
		    {  
		    	WebElement fillQuestion = driver.findElement(By.xpath("//input[@name='quest_blank_1']"));
		    	fillQuestion.sendKeys("Stuck");
		       	WebElement clickNextButton = driver.findElement(By.xpath("//a[text()='NEXT']"));
		       	clickNextButton.click();
		       	Thread.sleep(1000);
		    	WebElement clickContinueButton = driver.findElement(By.xpath("//a[text()='Continue']"));
		    	clickContinueButton.click();
		    	System.out.println("Fill in the blank executed");
		    }
		    else
		    {
		    	System.out.print("Fill in the blank element not display");
		    }
			
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Element is not present, hence not displayed as well");
		}

	    try {
		    if (driver.findElements(By.xpath("//p[text()='Click to place the Orange dot to indicate the bananas.']")).size()!=0)
		    {
		    	JavascriptExecutor js = (JavascriptExecutor) driver;
		    	js.executeScript("window.scrollBy(0,250)");
		    	Thread.sleep(1000);
		    	Robot robot = new Robot();
		    	robot.mouseMove(520,721);
		    	robot.delay(1500);
		        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click	
		        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click	
		       	WebElement clickNextButton = driver.findElement(By.xpath("//a[text()='NEXT']"));
		       	clickNextButton.click();
		       	Thread.sleep(1000);
		    	WebElement clickContinueButton = driver.findElement(By.xpath("//a[text()='Continue']"));
		    	clickContinueButton.click();
		    	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	//code to be executed if all the conditions are false  
		    	System.out.println("In side the hotspot element");
		    }	
		    else
		    {
		    	System.out.println("hotspot element not found");
		    }
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Element is not present, hence not displayed as well");
		}


	    
	}

}
