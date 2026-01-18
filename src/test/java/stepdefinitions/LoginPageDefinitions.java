package stepdefinitions;

import org.testng.Assert;

import actions.HomePageActions;
import actions.LoginPageActions;
import utils.ConfigReader;
import utils.EnvironmentReader;
import utils.Helper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageDefinitions {

    LoginPageActions objLogin = new LoginPageActions();
    HomePageActions objHomePage = new HomePageActions();

    @Given("User is on HRMLogin page")
    public void openUrl() {
    	//String url = ConfigReader.getProperty("base.url");
    	String url = EnvironmentReader.get("base.url");
        Helper.openPage(url);
    }

    @When("User enters username as {string} and password as {string}")
    public void goToHomePage(String userName, String passWord) {

        // login to application
        objLogin.login(userName, passWord);

        // go the next page

    }

    @When("User clicks on Forgot your Password Link")
    public void goToForgotYourPasswordPage() {

        objLogin.clickOnForgetYourPasswordLink();

    }

    @Then("User should be able to login successfully and new page open")
    public void verifyLogin() {
  
        // Verify home page
        Assert.assertTrue(objHomePage.getHomePageText().contains("dashboard"));
  
    }
    
    @Then("User should be able to see error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {

        // Verify home page
        Assert.assertEquals(objLogin.getErrorMessage(),expectedErrorMessage);

    }

    @Then("User should be able to see a message {string} below Username")
    public void verifyMissingUsernameMessage(String message) {

        Assert.assertEquals(objLogin.getMissingUsernameText(),message);
    }

}
