package rahulshettyacademy.stepDefinations;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatologue;
import rahushettyacademy.TestComponents.BaseTest;
public class StepDefinationImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatologue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page () throws IOException
	{
		landingPage = launchApplication();
	}
		
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException 
	{
		List<WebElement>products=productCatalogue.getProductList();	
		productCatalogue.addProductTocart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_Submit_order(String productName)
	{
		CartPage cartpage = productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductionDisplay(productName); 
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
    //^$ it will not work for the direct case string providing in the step itself it will work if data is driven from examples
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		
    }

    @Then("^\"([^\"]*)\"messge is displayed$")
    public void something_message_is_displayed(String strArg1)throws Throwable{
    	
    	Assert.assertEquals("Incorrect email password", landingPage.getErrorMessage());	
    	driver.close();
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
