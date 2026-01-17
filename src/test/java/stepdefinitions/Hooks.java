package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome"; // default
        }
        System.out.println("====================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("Browser Selected: " + browser);
        System.out.println("====================================");
        // Attach browser info to ChainTest report
        scenario.log("Execution Browser: " + browser);
        DriverFactory.initDriver(browser);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario FAILED: " + scenario.getName());
        } else {
            System.out.println("Scenario PASSED: " + scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}