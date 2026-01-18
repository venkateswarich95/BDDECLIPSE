package actions;

import org.openqa.selenium.support.PageFactory;
import locators.HomePageLocators;
import utils.Helper;
 
public class HomePageActions {
 
    HomePageLocators homePageLocators = null;
     
    public HomePageActions() {
           
        this.homePageLocators = new HomePageLocators();
   
        PageFactory.initElements(Helper.getDriver(),homePageLocators);
    }
   
    
    // Get the User name from Home Page
    public String getHomePageText() {
        String text = homePageLocators.homePageUserName.getText();
        System.out.println("Home Page Text: " + text);
        return text;
    }
   
}