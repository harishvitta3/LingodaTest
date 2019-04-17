//Reusable Functions Package
//This package is created to store class which consists of all the reusable functions 
//used in the application
package com.web.amazon.Pages;

//importing required packages
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.web.amazon.ObjectRepository.AmazonSearchObject;

 
public class AmazonSearchPage {
    //Variable declaration
	public static WebDriver driver;
	/*This method is used to validate product search  
	* Parameters: FolderName - It is used to name the folder with mentioned name under Screenshots
	*           SrchValue - Product to search in Amazon
	*           driver - driver object*/
	public static void searchProduct(String Folder,String SrchValue,WebDriver driver) throws InterruptedException {
	    //Wait for 2 seconds
		Thread.sleep(2000);
		
		//Enter the text in search box
		WebElement searchBox=driver.findElement(By.xpath(AmazonSearchObject.SearchBoxInput_XPATH.toString()));
		searchBox.sendKeys(SrchValue);
		Thread.sleep(1000);
		
		//Click on the search button
		WebElement searchBtn=driver.findElement(By.xpath(AmazonSearchObject.SearchBtn_XPATH.toString()));
		searchBtn.click();
		Thread.sleep(2000);
		
		//Verify whether search results are getting displayed
		boolean srchrslt = driver.findElements(By.xpath(AmazonSearchObject.SearchResults_XPATH.toString())).size()>0;
		if(srchrslt) {
		   WebElement searchRslt = driver.findElement(By.xpath(AmazonSearchObject.SearchResults_XPATH.toString()));
		   String Rslt = searchRslt.getText();
		   //System.out.println("Text displayed"+Rslt);
		   Thread.sleep(3000);
		   String sub = Rslt.substring(0,1);
		   int num2 = Integer.parseInt(sub);
		   //System.out.println(num2);
		    if(num2>0) {
			  System.out.println("Search results count is greater than Zero");
		}}else{
			Assert.fail("No Search results found!");
		}
		
		//Verify the product title with the search results
		WebElement searchTitle = driver.findElement(By.xpath(AmazonSearchObject.SearchTitle_XPATH.toString()));
		String TitleCompare = searchTitle.getText();
		    if(searchTitle.getText().contains("Batman"))
		      {
			     System.out.println("Batman text is present in the first search item");
		    }else {
			    Assert.fail("Batman text is not present in the first search item");
		    }
		
		    //Capture Price
		    WebElement price = driver.findElement(By.xpath(AmazonSearchObject.Price_XPATH.toString()));
		    String newprice = price.getText();
		    
		    //Capture Currency Symbol
		    WebElement currencysymbl = driver.findElement(By.xpath(AmazonSearchObject.CurrencySymbol_XPATH.toString()));
		    String newcurrencysymbl = currencysymbl.getText();
		    System.out.println("Price of the product is:"+newprice+ " & " +"Symbol :" +newcurrencysymbl);
		    
		    //Verify product has Reviews
		    boolean starRating = driver.findElements(By.xpath(AmazonSearchObject.StarRating_XPATH.toString())).size()>0;
			if(starRating) {
				System.out.println("Star rating is present for the product");
				
			}else {
				Assert.fail("Star rating is not present for the product");
			}
		    
			//Capture screenshot
			captureScreenshot(Folder,"SearchResult",driver);
		    
		    //click on first product link from the search results
		    searchTitle.click();
		    Thread.sleep(2000);
		    
		    //Capture screenshot
		    captureScreenshot(Folder,"ProductDetails", driver);
		    
		    //Verify the product title and Price with the above captured values
		    WebElement searchTitleAfterClick = driver.findElement(By.xpath(AmazonSearchObject.TitleAfterClick_XPATH.toString()));
		    String TileCompareAfter = searchTitleAfterClick.getText();
		    WebElement priceAfterClik = driver.findElement(By.xpath(AmazonSearchObject.PriceAfterClick_XPATH.toString()));
		    String PriceSubTxt = priceAfterClik.getText();
		    String Pricesub = PriceSubTxt.substring(4);
		      if(TileCompareAfter.contains(TitleCompare) && Pricesub.equals(newprice) )
		       {
			     System.out.println("Title & Price are matching");
		      }else {
			     Assert.fail("Title & Price are not matching");
		     }
	}
	
	/*This method is used to capture application screenshot during test execution
	* Parameters: FolderName - It is used to name the folder with mentioned name under Screenshots
	*           filename - It is used to create a individual screenshot file.
	*           driver - driver object*/
	public static File captureScreenshot(String FolderName,String filename, WebDriver driver) {
	
		File file = null;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now(); 
		System.out.println(dtf.format(localDate));
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Test: "+timestamp);
		
		@SuppressWarnings("deprecation") 
		 int str = timestamp.getHours();
		  
		 @SuppressWarnings("deprecation") 
		 int str1 = timestamp.getMinutes();
		  
		 @SuppressWarnings("deprecation") 
		 int str2 = timestamp.getSeconds();
		 String str4 = str+"-"+str1+"-"+str2;
		
		try {
			String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+FolderName+localDate;
			File folder = new File(filePath);
			if (!folder.exists()) {
				boolean result = false;
				try {
					folder.mkdir();
					result = true;
				} catch (Exception e) {

				}
				if (result) {
					//System.out.println("Folder with name " + filename + " created");
				}
			}
			file = new File(folder + "//" + filename +str4+".jpg");
			if (file.exists()) {
				if(file.delete()) {
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File(file.toString()));
				}
			} else {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(file.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
}
