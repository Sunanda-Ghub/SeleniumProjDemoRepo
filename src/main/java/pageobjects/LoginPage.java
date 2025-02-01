package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver){

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-email")
	private WebElement emailfield;
	
	public WebElement emailfield() {
		
		return emailfield;
	}
	
	@FindBy(id="input-password")
	private WebElement passwordfield;
	
	public WebElement passwordfields() {
		
		return passwordfield;
	}
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginbutton;
	
	public WebElement loginbutton() {
		
		return loginbutton;
	}
	

}
