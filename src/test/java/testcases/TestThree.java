package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TestThree extends Base {
	
	public WebDriver driver;
	@Test
	public void testhree() throws IOException, InterruptedException {
		
		System.out.println("This is test three");
		driver = initializeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		
		Thread.sleep(2000);
		driver.quit();
	}

}
