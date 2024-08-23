//167. Initialize Driver and create utility to launch App with BeforeMethod annotation
//166. Creating base test which holds common test configuration
//174. How to Create Screenshot Utility in Base Test class for catching Failed tests
//Headless mode  
// 
package rahushettyacademy.TestComponents;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	//Global properties setting up.
	//Properties class can read the properties
	//Creating a package and after that a file
	//Converting file input stream object getting project path
	//Loading is method file global properties 
	
	public WebDriver driver;
	public LandingPage landingPage; 
	
	public WebDriver initializerDriver() throws IOException 
	{
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\madhu\\eclipse\\java-2023-09\\eclipse\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis); 
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("fireFox"))	
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\madhu\\Downloads\\geckodriver-v0.33.0-win64 (3)\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			FirefoxOptions co = new FirefoxOptions();
			co.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			if(browserName.contains("headless")) 
			{
				options.addArguments("headless");
			}
			driver = new FirefoxDriver(options);
					
		}
		
		
		else if(browserName.equalsIgnoreCase("chrome")) 
		{
			
		}	
		
		else if(browserName.equalsIgnoreCase("edge")) 
		{
		
		}
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    //driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
	{	
		//Read JSon to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to hasMap JackSon DataBind 
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		//{map,map}		
		
	}
	

	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts .getScreenshotAs(OutputType.FILE);
		File file = new File (System.getProperty("user.dir")+"//reports//"+testCaseName+"png");
		FileUtils.copyFile(source, file);	
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException 
	{
		driver = initializerDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() 
	{
        driver.close();
	}
	
}
	

