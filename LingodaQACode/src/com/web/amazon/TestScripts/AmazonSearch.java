//Main Script Package
//This package is used to create main test using the reusable functions
package com.web.amazon.TestScripts;

//Import required packages
import java.io.File;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.web.amazon.ObjectRepository.AmazonSearchObject;
import com.web.amazon.Pages.AmazonSearchPage;
import com.web.amazon.Utility.ExcelReader;


public class AmazonSearch {

	//variable declaration
	public static WebDriver driver;
	private final static ExcelReader pathTestDataSheet = ExcelReader.getInstance(System.getProperty("user.dir")+"//TestData//" + "AmazonSearch.xlsx");	
	private final static Map<String, String> tData = pathTestDataSheet.getRowValue("AmazonSearch","AmazonSearch");
	public static String SrchValue=tData.get("SearchValue");
	public static String ScreenshotFolder=tData.get("FolderName");
	
	@BeforeMethod
	public static void lanuchUrl() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
	    driver = new ChromeDriver();
		driver.get("https://www.amazon.de/");
		driver.manage().window().maximize();
	}
	
	@Test
	public static void amazonsearch() throws Exception {
		AmazonSearchPage.searchProduct(ScreenshotFolder,SrchValue,driver);      
	}
	
	
	@AfterTest
	public static void closeBrowser() {
		driver.close();
	}
}
