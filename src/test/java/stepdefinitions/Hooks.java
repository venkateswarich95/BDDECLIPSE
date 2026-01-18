package stepdefinitions;

import org.testng.Reporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.EnvironmentReader;
import utils.ScreenshotUtils;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {
	
	private static boolean isFirstRun = true;

	@Before
	public void setup(Scenario scenario) {
		 if (isFirstRun) {
	            ScreenshotUtils.clearScreenshots(); // ✅ correct place
	            isFirstRun = false;
	        }

	   // String browser = ConfigReader.getProperty("browser");
		 // ✅ Browser now comes from env file (dev / qa / prod)
        String browser = EnvironmentReader.get("browser");

	    System.out.println("====================================");
	    System.out.println("Starting Scenario: " + scenario.getName());
	    System.out.println("Browser Selected: " + browser);
	    System.out.println("====================================");

	    // Attach browser info to ChainTest report
	    scenario.log("Execution Browser: " + browser);

	    DriverFactory.initDriver(); // browser comes from config.properties
	}

	@After
	 public void tearDown(Scenario scenario) {

		 if (scenario.isFailed()) {

	            String screenshotPath =
	                    ScreenshotUtils.takeScreenshot(scenario.getName());

	            // ✅ Attach ONLY to Cucumber
	            try {
	                scenario.attach(
	                        Files.readAllBytes(Paths.get(screenshotPath)),
	                        "image/png",
	                        "Failure Screenshot"
	                );
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        DriverFactory.quitDriver();
	    }
}