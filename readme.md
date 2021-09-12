# Orange HRM Automation

## Includes TestNg, Selenium webdriver Java, Allure Reporting

## Folder Struture :

- Utilities - Package
    - WebDriverManager.java
    - TestSetup.java
    - SeleniumHelper.java
    - JavaHelpers.java
    - Constants.java
- Page - Package
    - dashboard > DashboardPage.java   
    - employee
        - EmployeePage.java
        - SearchEmployeePage.java
    - login > Login.java
- Test - Package
    - login > loginTest.java
    - employee -> employeeTest.java

# testng.xml

```sh
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="OrangeHRM" parallel="classes">
	<!-- Parameters -->
	<parameter name="node" value="local" />
	<parameter name="browser" value="chrome-headless" />
	<!-- browser value can be : chrome ,chrome-headless,ie ,firefox -->
	<test name="LoginFeature">
		<classes>
			<class name="tests.login.LoginTest"/>
		</classes>
	</test>
	<!-- Test -->
	<test name="EmployeeFeature">
		<classes>
			<class name="tests.employee.EmployeeTest"/>
		</classes>
	</test>
	<!-- Test -->
</suite>
<!-- Suite -->
```
Note : While running the testng.xml and changing the parameter values as specified it will run all test case in particular given browser.

# Allure reporting
Add depdencies to maven pom.xml -> Add allure in your local machine. 
once all test case execution done execute allure serve command along with allure-result path.

```sh
allure serve c:\your project path\allure-report
```

OrangeHRM automation inlcudes Page object model along with OOPS concepts.

Note : Right now only chromedriver added to the utilties -> Driver -> chromedriver. 

