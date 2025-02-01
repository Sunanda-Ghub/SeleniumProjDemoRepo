package testcases;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	WebDriver driver;
	Logger log;

	@Test(dataProvider = "loginData")
	public void login(String email, String password, String exepectedstatus) throws IOException, InterruptedException {

//		FileInputStream fis1 = new FileInputStream("src\\main\\java\\resources\\data.properties");
////
//////		FileInputStream fis1 = new FileInputStream("propPath");
//		Properties prop = new Properties();
//		prop.load(fis1);

		LandingPage landingpage = new LandingPage(driver);
		
		Thread.sleep(2000);
		landingpage.AccountDropdown().click();
		log.debug("Clicked on Account dropdown");

		Thread.sleep(2000);
		landingpage.loginlink().click();

		log.debug("Clicked on Login Link");
		Thread.sleep(2000);

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailfield().sendKeys(email);
		log.debug("Email address got entered");
		
		loginpage.passwordfields().sendKeys(password);
		
		log.debug("Password got entered");
		
		loginpage.loginbutton().click();
		
		log.debug("Clicked on login Button");

		Thread.sleep(2000);

		AccountPage accountpage = new AccountPage(driver);

		String actualstatus = null;

		try {
			if (accountpage.editaccount().isDisplayed()) {
				actualstatus = "Successful";
				
				log.debug("User got logged in");
			}
		} catch (Exception e) {

			log.error("User couldn't login");
			actualstatus = "Failure";
		}

		Assert.assertEquals(actualstatus, exepectedstatus);
		log.info("LoginTest got passed");

	}
	
	@BeforeMethod
	public void OpenApplication() throws IOException, InterruptedException {
		
		log=LogManager.getLogger(LoginTest.class.getName());
		driver = initializeDriver();
		log.debug("Browser got Launched");
//		Thread.sleep(2000);
		driver.get(prop.getProperty("Url"));
	
		log.debug("Navigated to application URL");
	}

	@AfterMethod
	public void closure() {

		driver.quit();
		//log.debug("Browser got closed");
	}

	//need to use data provider when we need to use multiple sets of condition for one thing to satisfy
	@DataProvider
	public Object[][] loginData() {

		Object[][] data = { { "subhasree.sunanda.eee@gmail.com", "123456789", "Successful" },
				{ "demo@test.com", "90", "Failure" } };
		return data;
	}

}
