package resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;


public class Base {
	
	public Properties prop;
	
	public WebDriver driver;
	public WebDriver initializeDriver() throws IOException, InterruptedException {
		
		//to dynamically get the proper path of the property file
		String propPath = System.getProperty("user.dir")+"src\\main\\java\\resources\\data.properties";
		
		FileInputStream fis = new FileInputStream("src\\main\\java\\resources\\data.properties");
		
//		FileInputStream fis = new FileInputStream("propPath");
		prop = new Properties();
		
		prop.load(fis);
		
		Thread.sleep(2000);
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(2000);
		return driver;

	}
	
	public String takeScreenshots(String testName, ITestResult result) throws IOException {
		
		File SourceFile = ((TakesScreenshot) result).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
		
		return destinationFilePath;
	}
	
}
