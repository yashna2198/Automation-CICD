package learning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.abstractcomponents.AbstractComponents;

public class Productcatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public Productcatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".card-body") 
		List<WebElement> items;
	@FindBy(css="#toast-container") 
		WebElement waitinvisibleparam;
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By waitappearparam= By.cssSelector("div.la-3x");
	By waitclickablecart = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	public List<WebElement> getProducts() {
		waitUntilElementClickable(addToCart);
		System.out.println(items);
		return items;
	}
	
	public CartPage SelectToCart(List<WebElement> items, String productName) {
		WebElement product = items.stream().filter(item->item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		product.findElement(addToCart).click();
		waitUntilElementAppear(waitappearparam); 
		waitInvisibilityOfWebElement(waitinvisibleparam);
		waitUntilElementClickable(waitclickablecart);
		return new CartPage(driver);
	}
	
	
}
