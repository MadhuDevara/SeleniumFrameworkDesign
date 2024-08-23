package rahulshettyacademy.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
    
    WebDriver driver;
    
    @FindBy(css = ".totalRow button")
    WebElement checkoutELe;
    
    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;
    
    public CartPage(WebDriver driver) { 
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public Boolean VerifyProductionDisplay1(String productName) {
        Boolean match = cartProducts.stream().anyMatch(product -> product.getText()
                .equalsIgnoreCase(productName));
        return match;
    }
    
    public CheckoutPage goToCheckout() { 
        checkoutELe.click();
        return new CheckoutPage(driver);
    }
    
    public Boolean VerifyProductionDisplay(String productName) {
        Boolean match = cartProducts.stream().anyMatch(product -> product.getText()
                .equalsIgnoreCase(productName));
        return match;
    }
}


/*
package rahulshettyacademy.pageobjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(css =".totalRow button")
	WebElement checkoutELe;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver) 
	{ 
		super (driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductionDisplay1(String productName) 
	{
		Boolean match = cartProducts.stream().anyMatch(product-> product.getText()
				.equalsIgnoreCase(productName));
				return match;
	}
	
	public CheckoutPage goToCheckout() 
	{ 
		checkoutELe.click();
		return new CheckoutPage(driver);
		
	}
	public Boolean VerifyProductionDisplay(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
*/