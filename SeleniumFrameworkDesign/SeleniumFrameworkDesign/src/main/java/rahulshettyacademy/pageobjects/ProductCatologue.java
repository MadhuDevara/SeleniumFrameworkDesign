
//160,161 Creating page object classes for login screen and migrate the test from another class
//Creating abstract classes to reuse the common methods /code in framework

package rahulshettyacademy.pageobjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import rahulshettyacademy.AbstractComponents.AbstractComponent;
public class ProductCatologue extends AbstractComponent {
	
	WebDriver driver;
	
	//Constructor
	//Passing the driver to the local variable and giving life to driver
	//Initialization driver, this inside constructor will be executed first
	
	public ProductCatologue(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	//PageFactory is exclusively for driver.findElements only
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	

	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productysBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productysBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) 
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement (By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	//Question: can you apply pageFactory with in webElement.findElement
	//No its not possible
	
	public void addProductTocart(String productName) throws InterruptedException 
	{
		WebElement prod = getProductByName(productName); 
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	

}
