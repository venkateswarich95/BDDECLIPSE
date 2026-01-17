package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.time.Duration;
import java.util.*;


public class GenericMethods extends BDD_TestBase {


    /* ============================== ELEMENT ACTIONS ============================== */

    public static void clickOnElement(By ele, String elementName) {
        System.out.println(" Clicking on element: " + elementName);
        WaitMethods.wait_for_element_to_be_clickable(ele);
        highLightElement(ele);
        getElement(ele).click();
        System.out.println(" Click successful: " + elementName);
    }

    public static void clickOnElement_Using_JS(By ele, String elementName) {
        System.out.println("JS Click on element: " + elementName);
        WaitMethods.wait_for_element_to_be_clickable(ele);
        JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDriver();
        executor.executeScript("arguments[0].click()", getElement(ele));
    }

    public static void enter_text(By element, String inputText, String fieldName) throws InterruptedException {
        System.out.println(" Entering text into: " + fieldName);
        WaitMethods.wait_for_element_present(element);
        getElement(element).clear();
        Thread.sleep(500);
        getElement(element).sendKeys(inputText);
        System.out.println("Text entered: " + inputText);
    }

    public static void select_dropdown(By ele, String type, String value, String eleName) {
        System.out.println("Selecting dropdown: " + eleName);
        WaitMethods.wait_for_element_present(ele);
        Select dropdown = new Select(getElement(ele));

        switch (type.toLowerCase()) {
            case "visibletext":
                dropdown.selectByVisibleText(value);
                break;
            case "value":
                dropdown.selectByValue(value);
                break;
            case "index":
                dropdown.selectByIndex(Integer.parseInt(value));
                break;
            default:
                System.out.println(" Invalid dropdown type");
        }
    }

    /* ============================== ELEMENT HELPERS ============================== */

    public static int sizeOfElement(By ele) {
        return DriverFactory.getDriver().findElements(ele).size();
    }

    public static List<WebElement> getListOfElements(By ele) {
        return DriverFactory.getDriver().findElements(ele);
    }

    public static WebElement getElement(By ele) {
        return DriverFactory.getDriver().findElement(ele);
    }

    public static String getElementText(By ele) {
        return getElement(ele).getText();
    }

    public static boolean is_element_present(By element) {
        try {
            return getElement(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean is_element_enabled(By element) {
        try {
            boolean enabled = getElement(element).isEnabled();
            System.out.println(" Element enabled: " + enabled);
            return enabled;
        } catch (Exception e) {
            return false;
        }
    }

    /* ============================== WINDOW & SCROLL ============================== */

    public static void scroll_down_to_element(By ele) {
        ((JavascriptExecutor) DriverFactory.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", getElement(ele));
    }

    public static void navigate_to_next_window() {
        for (String win : DriverFactory.getDriver().getWindowHandles()) {
        	DriverFactory.getDriver().switchTo().window(win);
        	DriverFactory.getDriver().manage().window().maximize();
        }
    }

    public static void navigate_to_parent_window() {
    	DriverFactory.getDriver().switchTo().window(DriverFactory.getDriver().getWindowHandles().toArray()[0].toString());
    }

    /* ============================== PAGE VALIDATION ============================== */

    public static boolean validatePageTitle(String expected) {
        System.out.println(" Validating page title: " + expected);
        String title = DriverFactory.getDriver().getTitle();
        return title.contains(expected);
    }

    public static boolean verifyPageAssert(By element, String pageName) {
        System.out.println(" Verifying page: " + pageName);
        return sizeOfElement(element) > 0;
    }

    /* ============================== ACTIONS ============================== */

    public static void clickOn_element_using_actions(By element, String elementName) {
        System.out.println("Actions click on: " + elementName);
        Actions act = new Actions(DriverFactory.getDriver());
        act.moveToElement(getElement(element)).click().build().perform();
    }

    /* ============================== ALERTS ============================== */

    public static void accept_alert() {
        System.out.println("Checking alert");
        if (isAlertPresent()) {
            Alert alert = DriverFactory.getDriver().switchTo().alert();
            System.out.println(" Alert text: " + alert.getText());
            alert.accept();
        }
    }

    public static boolean isAlertPresent() {
        try {
            new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /* ============================== ASSERTIONS ============================== */

    public static void assertEquals_with_message(String actual, String expected, String message) {
        System.out.println("[ASSERT] " + message);
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertTrue_with_message(boolean condition, String message) {
        System.out.println("[ASSERT] " + message);
        Assert.assertTrue(condition, message);
    }

    public static String get_random_Integer(int range) {
        int random = new Random().nextInt(range) + 1;
        System.out.println(" Random number: " + random);
        return String.valueOf(random);
    }

    public static double replaceCurrency(String value) {
        return Double.parseDouble(value.replaceAll("[^0-9.]", ""));
    }

    public static void highLightElement(By element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("arguments[0].style.border='3px solid red'", getElement(element));
            Thread.sleep(300);
            js.executeScript("arguments[0].style.border=''", getElement(element));
        } catch (Exception ignored) {}
    }

   
}









