package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonFunctions;

public class Login extends CommonFunctions{
	
	public Login() {
		PageFactory.initElements(super.driver, this);
	}
	
	@FindBy(name="username")
	private WebElement userText;
	
	@FindBy(name="password")
	private WebElement passText;
	
	@FindBy(xpath="//button")
	private WebElement logBtn;
	
	public WebElement getUserText() {
		return userText;
	}
	public WebElement getPassText() {
		return passText;
	}
	public WebElement getLogBtn() {
		return logBtn;
	}
	

}
