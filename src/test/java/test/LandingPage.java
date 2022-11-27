package test;

import org.testng.annotations.Test;

import resource.Base;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

public class LandingPage extends Base{
	WebDriver driver;
	


	@BeforeTest
	public void beforeTest() throws IOException {
		driver = initializeDriver();
	}

	// Verify the title of the application
	@Test
	void testTitle() {
		driver.get(prop.getProperty("baseUrl"));
		String expectedTitle = "Address Book";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
