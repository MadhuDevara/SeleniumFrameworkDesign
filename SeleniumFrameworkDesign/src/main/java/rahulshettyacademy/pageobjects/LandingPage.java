
//159 Creating page object classes for login screen and migrate the test from another class

package rahulshettyacademy.pageobjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	//Constructor
	//Passing the driver to the local variable and giving life to driver
	
	public LandingPage(WebDriver driver) 
	{
	
	super(driver);// Sending the constructor from here to parent by super
	//Initialization driver, this inside constructor will be executed first
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail")); can also be
	//Written in short form by using PageFactory @findBy annotations
	//PageFactory is used to minimize the syntax code 
	//@FindBy annotation is used to minimize
	//How does the annotations know about driver then
	//method .initElements which we have to write it take care of 
	//Driver argument which me said in the method.
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	//Write as per XPath,CSS,id
	
	@FindBy(id="login")
	WebElement submit;

	//.ng-tns-c4-2.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	//Actions Methods
	
	public ProductCatologue loginApplication(String email, String password ) 
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatologue productCatalogue = new ProductCatologue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() 
	{
		waitForWebElementToAppear(errorMessage);		
		return errorMessage.getText();
	}
		
	
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
