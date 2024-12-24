package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonFunctions;

public class DashBoard extends CommonFunctions{

	public DashBoard() {
		PageFactory.initElements(super.driver, this);
	}
	
	@FindBy(xpath="//h6")
	private WebElement dashText;
	
	public WebElement getdashText() {
		return dashText;
	}
}
