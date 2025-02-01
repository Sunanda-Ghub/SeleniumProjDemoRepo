package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import resources.Base;

public class TestFour extends Base {
	
	public WebDriver driver;
	
	@Test
	public void testfour() throws IOException, InterruptedException {
		
		System.out.println("This is test four");
		
		driver = initializeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		
		Thread.sleep(2000);
		
		Assert.assertTrue(false);
		driver.quit();
		
	}

}
