package utilities;



public class Constants {
	public static final String PROPERTYFILE = "test-input/constants.properties";

	// Environment
	public static final String Env = setSystemVariable("Env");

	public static final int PAGEFACTORY_WAIT_DURATION = Integer.parseInt(setSystemVariable("PageFactoryWaitDuration"));

	public static final int WEBDRIVER_WAIT_DURATION = Integer.parseInt(setSystemVariable("WebDriverWaitDuration"));
	public static final String SCREENSHOT_LOCATION= JavaHelpers.getPropertyValue(PROPERTYFILE,"ScreenshotLocation");


	// Set System variable
	private static String setSystemVariable(String variableName) {
		// Reading from system properties.
		String environment = System.getProperty(variableName);

		// if not specified via command line, take it from constants.properties file
		if (environment == null || environment.isEmpty()) {
			environment = JavaHelpers.getPropertyValue(PROPERTYFILE, variableName);
		}
		return environment;
	}

}
