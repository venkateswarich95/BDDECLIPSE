package utils;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    // ===================== CONSTRUCTOR =====================
    public BrowserUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===================== WINDOW HANDLING =====================

    /** Switch to newly opened window */
    public void switchToNewWindow() {
        String parent = driver.getWindowHandle();
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                return;
            }
        }
        throw new RuntimeException("No new window found to switch");
    }

    /** Switch window by exact title */
    public void switchToWindowByTitle(String title) {
        wait.until(driver -> driver.getWindowHandles().size() >= 1);
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            if (driver.getTitle().equalsIgnoreCase(title)) {
                return;
            }
        }
        throw new RuntimeException("Window with title not found: " + title);
    }

    /** Switch window by partial title */
    public void switchToWindowByPartialTitle(String partialTitle) {
        wait.until(driver -> driver.getWindowHandles().size() >= 1);
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(partialTitle)) {
                return;
            }
        }
        throw new RuntimeException("Window with partial title not found: " + partialTitle);
    }

    /** Switch window by URL */
    public void switchToWindowByURL(String partialURL) {
        wait.until(driver -> driver.getWindowHandles().size() >= 1);
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            if (driver.getCurrentUrl().contains(partialURL)) {
                return;
            }
        }
        throw new RuntimeException("Window with URL not found: " + partialURL);
    }

    /** Close all child windows and switch back to parent */
    public void closeAllChildWindows() {
        String parent = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }

    /** Get current window title & URL */
    public String getCurrentWindowDetails() {
        return "Title: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl();
    }

    // ===================== ALERT HANDLING =====================

    /** Accept alert */
    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    /** Dismiss alert */
    public void dismissAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }

    /** Get alert text */
    public String getAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    /** Send text to alert and accept */
    public void sendTextToAlert(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    /** Validate alert text */
    public void validateAlertText(String expectedText) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualText = alert.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(
                "Alert text mismatch. Expected: " + expectedText + " | Actual: " + actualText
            );
        }
    }

    // ===================== POP-UP HANDLING =====================

    /** File upload popup */
    public void uploadFile(By locator, String filePath) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(filePath);
    }

    /** Handle browser permission pop-ups */
    public void handleBrowserPopup() {
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
    }

    // ===================== FRAME HANDLING =====================

    public void switchToFrameByIndex(int index) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    public void switchToFrameByNameOrId(String nameOrId) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
    }

    public void switchToFrameByElement(By locator) {
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.switchTo().frame(frame);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // ===================== WAIT UTILITIES =====================

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ===================== SAFE ELEMENT ACTIONS =====================

    public void click(By locator) {
        waitForClickability(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    // ===================== JS UTILITIES =====================

    public void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickUsingJS(By locator) {
        WebElement element = waitForClickability(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // ===================== PAGE LOAD =====================

    public void waitForPageLoad() {
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }
    
    //===========================DATE PICKER============================
    
    /**
     * Selects date by sending keys directly to an input field.
     * Best suited for <input type="date"> elements.
     *
     * @param locator WebElement locator of date input
     * @param date    Date value in format yyyy-MM-dd
     */
    public void selectDateUsingSendKeys(By locator, String date) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(date);
    }

    /**
     * Selects a date from a calendar widget by navigating month/year
     * and clicking on the desired day.
     * Works for most custom UI calendars (jQuery, React, Angular).
     *
     * @param calendarBtn       Locator to open calendar
     * @param monthYearLocator Locator showing current month & year
     * @param nextBtn           Locator for next month button
     * @param prevBtn           Locator for previous month button
     * @param dayLocator        Locator for all selectable day elements
     * @param expectedMonthYear Expected month & year (e.g., "March 2026")
     * @param day               Day to select (e.g., "15")
     */
    public void selectDateFromCalendar(By calendarBtn,
                                       By monthYearLocator,
                                       By nextBtn,
                                       By prevBtn,
                                       By dayLocator,
                                       String expectedMonthYear,
                                       String day) {

        click(calendarBtn);

        while (true) {
            String actualMonthYear = waitForVisibility(monthYearLocator).getText();
            if (actualMonthYear.equalsIgnoreCase(expectedMonthYear)) {
                break;
            }
            click(nextBtn);
        }

        driver.findElements(dayLocator)
                .stream()
                .filter(e -> e.getText().equals(day))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Day not found: " + day))
                .click();
    }

    /**
     * Selects date by removing readonly attribute using JavaScript.
     * Useful when calendar input is disabled or readonly.
     *
     * @param locator WebElement locator of date input
     * @param date    Date value to set
     */
    public void selectDateUsingJS(By locator, String date) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].removeAttribute('readonly')", element);
        element.clear();
        element.sendKeys(date);
    }

    //==================================WEB TABLE=======================
    
    /**
     * Returns the total number of rows in a table.
     *
     * @param rowLocator Locator for table rows
     * @return number of rows
     */
    public int getRowCount(By rowLocator) {
        return driver.findElements(rowLocator).size();
    }

    /**
     * Returns the total number of columns in a table.
     *
     * @param columnLocator Locator for table columns
     * @return number of columns
     */
    public int getColumnCount(By columnLocator) {
        return driver.findElements(columnLocator).size();
    }

    /**
     * Fetches text value from a specific table cell using row and column index.
     *
     * @param table WebElement of table
     * @param row   Row index (starts from 1)
     * @param col   Column index (starts from 1)
     * @return cell text value
     */
    public String getCellValue(WebElement table, int row, int col) {
        return table.findElement(
                By.xpath(".//tr[" + row + "]/td[" + col + "]")
        ).getText();
    }

    /**
     * Clicks on a table cell that matches the given text.
     * Useful for selecting records dynamically.
     *
     * @param tableLocator Locator of table
     * @param text         Cell text to match
     */
    public void clickCellByText(By tableLocator, String text) {
        WebElement table = waitForVisibility(tableLocator);

        table.findElements(By.tagName("td"))
                .stream()
                .filter(cell -> cell.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cell not found: " + text))
                .click();
    }

    /**
     * Clicks an action (Edit/Delete/View) inside a specific row
     * identified by unique row text.
     *
     * @param tableLocator Locator of table
     * @param rowText      Unique text in the row
     * @param actionLocator Locator of action element inside row
     */
    public void clickActionInRow(By tableLocator,
                                  String rowText,
                                  By actionLocator) {

        WebElement table = waitForVisibility(tableLocator);

        WebElement row = table.findElements(By.tagName("tr"))
                .stream()
                .filter(r -> r.getText().contains(rowText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Row not found: " + rowText));

        row.findElement(actionLocator).click();
    }

    /**
     * Verifies whether a specific value exists anywhere in the table.
     *
     * @param tableLocator Locator of table
     * @param value        Value to search
     * @return true if present, false otherwise
     */
    public boolean isRecordPresentInTable(By tableLocator, String value) {
        WebElement table = waitForVisibility(tableLocator);
        return table.getText().contains(value);
    }

    /**
     * Returns all values from a specific column in a table.
     *
     * @param tableLocator Locator of table
     * @param columnIndex  Column index (starts from 1)
     * @return List of column values
     */
    public List<String> getColumnData(By tableLocator, int columnIndex) {
        WebElement table = waitForVisibility(tableLocator);

        return table.findElements(By.xpath(".//tr/td[" + columnIndex + "]"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    
}
