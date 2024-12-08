package learning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.abstractcomponents.AbstractComponents;


public class CartPage extends AbstractComponents {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3") 
		private List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button")
		WebElement proceedcheckout;
	
	
	public boolean verifyItems(String productName) {
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage checkOut() {
		proceedcheckout.click();
		return new CheckOutPage(driver);
	}
	
	
}
