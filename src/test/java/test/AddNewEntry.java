package test;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import resource.Base;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;


public class AddNewEntry extends Base{
	WebDriver driver;
	
	@BeforeTest
	public void initializePage() throws IOException
	{
		driver = initializeDriver();
	}
	
  @Test(dataProvider = "getTestData")
  public void addNewEntry(String entryType, String fname, String lname, String addline1, String city, 
		  String province, String country, String postCode,
		  String pType, String phone) throws IOException, InterruptedException{
	  driver.get(prop.getProperty("baseUrl"));
	  driver.findElement(By.linkText("Add New Entry")).click();
		Select addressType = new Select(driver.findElement(By.id("addr_type")));
		addressType.selectByValue(entryType);
		driver.findElement(By.id("addr_first_name")).sendKeys(fname);
		driver.findElement(By.id("addr_last_name")).sendKeys(lname);
		driver.findElement(By.id("addr_addr_line_1")).sendKeys(addline1);
		driver.findElement(By.id("addr_city")).sendKeys(city);
		driver.findElement(By.id("addr_region")).sendKeys(province);
		driver.findElement(By.id("addr_country")).sendKeys(country);
		driver.findElement(By.id("addr_post_code")).sendKeys(postCode);
		Select phoneType = new Select(driver.findElement(By.id("addr_phone_1_type")));
		phoneType.selectByValue(pType);
		driver.findElement(By.id("addr_phone_1")).sendKeys(phone);
		driver.findElement(By.id("submit_button")).click();
		String expectedText = "The new address book entry was added successfully";
		String actualText = driver.findElement(By.xpath("//h2[contains(text(),'The new address book entry was added successfully')]")).getText();
		assertEquals(expectedText , actualText);
		Reporter.log("Executed Add New Entry method");
  }
  

  @DataProvider
  public Iterator<Object[]> getTestData() throws IOException, CsvException {
		ArrayList<Object[]> testData = TestUtils.getDataReadCSV();
		return testData.iterator();
  }
  
  @AfterTest
	public void tearDown() throws IOException
	{
		driver.quit();
	}
}
