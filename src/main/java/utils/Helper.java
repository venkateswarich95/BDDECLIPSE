package utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;

public class Helper {

    public static final int TIMEOUT = 10;

    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public static void openPage(String url) {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.get(url);
    }
}
