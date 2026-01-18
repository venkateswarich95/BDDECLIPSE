package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        if (driver.get() == null) {

         //   String browser = ConfigReader.getProperty("browser");
        	// ✅ Browser now comes from environment-specific properties
            String browser = EnvironmentReader.get("browser");

            if (browser == null || browser.isEmpty()) {
                throw new RuntimeException("Browser is not specified in config.properties");
            }

            switch (browser.toLowerCase()) {

                case "chrome":
                    driver.set(new ChromeDriver());
                    break;

                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    driver.set(new EdgeDriver());
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Browser not supported: " + browser
                    );
            }
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
