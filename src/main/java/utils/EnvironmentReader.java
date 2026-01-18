package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class EnvironmentReader {

    private static Properties properties = new Properties();

    static {
        try {
            String env = System.getProperty("env");
            if (env == null) {
                env = "qa"; // default environment
            }

            String path = System.getProperty("user.dir")
                    + "/src/test/resources/env/"
                    + env + ".properties";

            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);

            System.out.println("Running tests in ENV: " + env.toUpperCase());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load environment properties file");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
