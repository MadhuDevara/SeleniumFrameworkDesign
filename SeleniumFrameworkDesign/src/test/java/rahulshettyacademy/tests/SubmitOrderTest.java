//154 Selenium program to retrieve product and add to cart on java streams
//162 Converting to Page Object File
//Verify all the products and verify by grabbing elements by finding Common elements 
//Complete page object migration.
//optimizing the code and bringing new concepts.
//Concepts is make the code short.Test2
//Separating test will be easy to find bugs. 	
//Groups to run separately, Just add comma to add groups for different types of Tests.
//172 Integration of HashMap to Data provider to send the data as one Hash object.
//Catching the parameters and creating HashMap to send parameters
//173. How to read the data from Json files and create the list of HashMaps for testing
//Creating a package and a file extracting data from file
//174. How to Create Screenshot Utility in Base Test class for catching Failed tests

package rahulshettyacademy.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatologue;
import rahushettyacademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
//Orders		
		@Test(dataProvider="gatData",groups = {"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException 
		{
			
		ProductCatologue productCatalogue =landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products=productCatalogue.getProductList();	
		productCatalogue.addProductTocart(input.get("product"));
		CartPage cartpage = productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductionDisplay(input.get("product")); 
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		}

//To verify ZaraCoat 3 is displaying in orders page
//dependsOnMethods Run after SubmitOrderTest		
//Dependency Test 
//ZARA COAT 3

		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest() 
		{ 
			ProductCatologue productCatalogue =landingPage.loginApplication("madhudevara.to@gmail.com", "Devara@85");
			OrderPage orderPage=productCatalogue.goToOrdersPage();
			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		}
		
//Data Provider jSon concepts.
//Data provider help you to driver the data and pass the multiple data sets.		
//Its used to run the tests to run two different data sets.		
//Two dimensional array		
//Object accepts all types data	
//Attaching data provider main test By adding (dataProvider="getData")	
//Another map details need to send create another has map.		
		
		public String getScreenshot(String testCaseName) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts .getScreenshotAs(OutputType.FILE);
			File file = new File (System.getProperty("user.dir")+"//reports//"+testCaseName+"png");
			FileUtils.copyFile(source, file);	
			return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		}
		
		//Extent Reports 
		
		@DataProvider
		public Object[][] gatData() throws IOException 
		{
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("email", "madhudevara.to@gmail.com");
//			map.put("password", "Devara@85");
//			map.put("product", "ZARA COAT 3");
			
//			HashMap<String,String> map1 = new HashMap<String,String>();
//			map1.put("email", "to.madhu@yahoo.com");
//			map1.put("password", "Devara@85");
//			map1.put("product", "ADIDAS ORIGINAL");

//Call Json data map			
			
			List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\madhu\\eclipse\\java-2023-09\\eclipse\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrdeFile.json");
			return new Object [][] {{data.get(0)},{data.get(1)}};
			
		}
		
//Another way	
//@DataProvider
//public Object[][] get Data()
//{ 
//return new Object[][] {{"madhudevara.to@gmail.com","Devara@85","Zara COAT 3"}
//,{"to.madhu@yahoo.com","Devara@85","ADIDAS ORIGINAL");
//}
	
		
		
}
