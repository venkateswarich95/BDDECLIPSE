package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitMethods extends BDD_TestBase{
    
    public static boolean wait_for_element_to_be_clickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return true;
    }


    public static boolean wait_for_element_to_be_clickable(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return true;
    }

    public static boolean wait_for_element_present(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return true;
    }

    // Wait for a WebElement to be present (visible)
    public static boolean wait_for_element_present(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }
    
    public static void wait_for_page_load(int time)    {
    	DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public static void wait_in_seconds(int i) throws InterruptedException    {
        Thread.sleep(1000*i);
    }

    public static void waitTillElementNotVisible(By wb){
        WebDriverWait wait=new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(wb));
    }
}
