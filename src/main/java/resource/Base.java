package resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	private WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException
	{
		prop= new Properties();
		String path=System.getProperty("user.dir");
		InputStream fis = new FileInputStream(path+"\\src\\main\\java\\resource\\application.properties");
		prop.load(fis);
		String selectedBrowser=prop.getProperty("browser");

		if(selectedBrowser.equalsIgnoreCase("chrome"))
		{

			System.out.println("Before Test method is called for "+selectedBrowser);
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
//			driver.get("https://c354-192-159-177-240.ngrok.io/index.php");
		}
		else if(selectedBrowser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else if(selectedBrowser.equals("IE"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
