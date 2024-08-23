//154 Selenium program to retrieve product and add to cart on java streams
//162 Converting to Page Object File
//Verify all the products and verify by grabbing elements by finding Common elements 
//Complete page object migration.
//optimizing the code and bringing new concepts.
//169. Implement Test Strategy for Framework on how tests are divided based on modules

package rahulshettyacademy.tests;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sun.net.httpserver.Authenticator.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatologue;
import rahushettyacademy.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

		//If we need to reRun failed test again we use retryAalyzer
	
		@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void LoginErrorValidations() throws IOException, InterruptedException 
		{
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("madhudevara.to@gmail.com", "Devara@85");
		Assert.assertEquals("Incorrect email password", landingPage.getErrorMessage());	
		}

		@Test
		public void productErrorValidations() throws InterruptedException, IOException 
		{
		String productName = "ZARA COAT 3";
		ProductCatologue productCatalogue =landingPage.loginApplication("to.madhu@yahoo.com","Devara@85");
		List<WebElement>products=productCatalogue.getProductList();	
		productCatalogue.addProductTocart(productName);
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductionDisplay("ZARA COAT 33"); 
		Assert.assertFalse(match);
		}
	
}
