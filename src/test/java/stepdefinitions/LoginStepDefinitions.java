package stepdefinitions;

import io.cucumber.java.en.*;
import pages.DashBoard;
import pages.Login;
import utilities.CommonFunctions;

import org.openqa.selenium.WebElement;

public class LoginStepDefinitions {
	
	DashBoard db;
	Login lo;
    

    @Given("I navigate to the OrangeHRM login page {string}")
    public void navigate_to_login_page(String url) {
    	
    	CommonFunctions.launchBrowser();
    	CommonFunctions.launchUrl(url);
        
    }

    @When("I enter username {string} and password {string}")
    public void enter_credentials(String username, String password) {
    	lo = new Login();
    	WebElement usernameBox = lo.getUserText();
    	usernameBox.sendKeys(username);
    	WebElement passwordBox = lo.getPassText();
    	passwordBox.clear();
    	passwordBox.sendKeys(password);
    	WebElement loginBtn =  lo.getLogBtn();
    	loginBtn.click();
    }

    @Then("I should be redirected to the dashboard")
    public void verify_dashboard_redirection() {
    	db = new DashBoard();
    	String dashText = db.getdashText().getText();
        assert dashText.equals("Dashboard") : "Login failed";
        
        
    }
}
