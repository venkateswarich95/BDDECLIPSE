package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BDD_TestBase {
    public static RemoteWebDriver driver;
    public static String SystemName;
    public RemoteWebDriver getDriver() {
        return driver;
    }

    static String selectBrowser_WeekDays(){

        String browser=null;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String Day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        if(Day.contentEquals("Monday")||Day.contentEquals("Thursday")||Day.contentEquals("Sunday")){
            browser="chrome";
        }else if(Day.contentEquals("Tuesday")||Day.contentEquals("Friday")){
            browser="edge";
        }else if(Day.contentEquals("Wednesday")||Day.contentEquals("Saturday")){
            browser="edge";
        }
        //||Day.contentEquals("Sunday") Use this for Sunday
        return browser;
    }

    public static void select_browser() {

        String browser = selectBrowser_WeekDays();
        if (browser.equalsIgnoreCase("chrome")) {
            Map<String, Object> prefs = new HashMap<String, Object>();
            //To Turns off multiple download warning
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
            //Turns off download prompt
            prefs.put("download.prompt_for_download", false);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("-incognito");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");

            if (System.getProperty("user.dir").contains("jenkins")) {
                options.addArguments("--headless");
                System.out.println("==========================Browser running in headless mode=======================");
            }
            System.out.println("test case execution started on===>" + browser);
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edge_options = new EdgeOptions();
            edge_options.addArguments("--window-size=1920,1080");
            edge_options.addArguments("--remote-allow-origins=*");
            if (System.getProperty("user.dir").contains("jenkins")) {
                edge_options.addArguments("--headless");
                System.out.println("==========================Browser running in headless mode=======================");
            }
            driver = new EdgeDriver(edge_options);
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        String browserVersion = (String)cap.getCapability("browserVersion");
    }
    
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        try {
            if(driver!=null) {
                driver.quit();
            }
            Thread.sleep(3000);
            System.out.println("browser closed");
        } catch (NoSuchSessionException e) {
            System.out.println("The browser was already closed (or) there is no active browser to close");
        } catch (NoSuchWindowException e) {
            System.out.println("The window was already closed (or) there is no window to close");
        }
    }

}
