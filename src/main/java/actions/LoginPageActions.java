package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import locators.LoginPageLocators;
import utils.DriverFactory;
import utils.WaitMethods;

public class LoginPageActions {

    private LoginPageLocators loginPageLocators;

    public LoginPageActions() {
        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(DriverFactory.getDriver(), loginPageLocators);
    }

    // Set user name in textbox
    public void setUserName(String strUserName) {
        System.out.println("Entering username: " + strUserName);
        WaitMethods.wait_for_element_present(loginPageLocators.userName);
        loginPageLocators.userName.clear();
        loginPageLocators.userName.sendKeys(strUserName);
        System.out.println("Username entered successfully.");
    }

    // Set password in password textbox
    public void setPassword(String strPassword) {
        System.out.println("Entering password.");
        WaitMethods.wait_for_element_present(loginPageLocators.password);
        loginPageLocators.password.clear();
        loginPageLocators.password.sendKeys(strPassword);
        System.out.println("Password entered successfully.");
    }

    // Click on login button
    public void clickLogin() {
        System.out.println("Clicking on Login button.");
        WaitMethods.wait_for_element_to_be_clickable(loginPageLocators.login);
        loginPageLocators.login.click();
        System.out.println("Login button clicked successfully.");
    }

    // Get the title of Login Page
    public String getLoginTitle() {
        WaitMethods.wait_for_element_present(loginPageLocators.titleText);
        String title = loginPageLocators.titleText.getText();
        System.out.println("Login page title: " + title);
        return title;
    }

    // Get the error message when username is blank
    public String getMissingUsernameText() {
        WaitMethods.wait_for_element_present(loginPageLocators.missingUsernameErrorMessage);
        String msg = loginPageLocators.missingUsernameErrorMessage.getText();
        System.out.println("Missing username message: " + msg);
        return msg;
    }

    // Get the error message when password is blank
    public String getMissingPasswordText() {
        WaitMethods.wait_for_element_present(loginPageLocators.missingPasswordErrorMessage);
        String msg = loginPageLocators.missingPasswordErrorMessage.getText();
        System.out.println("Missing password message: " + msg);
        return msg;
    }

    // Get the general error message
    public String getErrorMessage() {
        WaitMethods.wait_for_element_present(loginPageLocators.errorMessage);
        String msg = loginPageLocators.errorMessage.getText();
        System.out.println("Error message displayed: " + msg);
        return msg;
    }

    // LinkedIn Icon is displayed
    public boolean isLinkedInIconDisplayed() {
        WaitMethods.wait_for_element_present(loginPageLocators.linkedInIcon);
        boolean displayed = loginPageLocators.linkedInIcon.isDisplayed();
        System.out.println("LinkedIn icon displayed: " + displayed);
        return displayed;
    }

    // Facebook Icon is displayed
    public boolean isFaceBookIconDisplayed() {
        WaitMethods.wait_for_element_present(loginPageLocators.faceBookIcon);
        boolean displayed = loginPageLocators.faceBookIcon.isDisplayed();
        System.out.println("Facebook icon displayed: " + displayed);
        return displayed;
    }

    // Click on "Forgot Your Password" link
    public void clickOnForgetYourPasswordLink() {
        System.out.println("Clicking on 'Forgot Your Password' link.");
        WaitMethods.wait_for_element_to_be_clickable(loginPageLocators.ForgotYourPasswordLink);
        loginPageLocators.ForgotYourPasswordLink.click();
        System.out.println("'Forgot Your Password' link clicked successfully.");
    }

    // Complete login action
    public void login(String strUserName, String strPassword) {
        System.out.println("Starting login process...");
        this.setUserName(strUserName);
        this.setPassword(strPassword);
        this.clickLogin();
        System.out.println("Login process completed.");
    }
}
