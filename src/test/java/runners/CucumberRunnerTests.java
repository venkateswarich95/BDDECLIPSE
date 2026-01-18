package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        tags = "@ValidCredentials",
        plugin = {
                "pretty",
                "html:target/cucumber-reports",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        }
)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

    // ✅ Enable parallel scenario execution
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
