package utils;

import utils.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
	 private static final String SCREENSHOT_DIR =
	            System.getProperty("user.dir") + "/screenshots/failures";

    public static String takeScreenshot(String scenarioName) {

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String screenshotDir = System.getProperty("user.dir")
                + "/screenshots/failures/";

        new File(screenshotDir).mkdirs(); // create folder if not exists

        String screenshotPath = screenshotDir
                + scenarioName.replaceAll(" ", "_")
                + "_" + timestamp + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            Files.copy(source.toPath(), destination.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
    
    public static void clearScreenshots() {
        File dir = new File(SCREENSHOT_DIR);

        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                }
            }
            System.out.println("Old screenshots cleared");
        }
    }
}
