package learning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class orderPage {
	
	WebDriver driver;
	public orderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//td[2]") 
	List<WebElement> myOrders;
	
	public boolean myLatestOrder(String productName) {
		boolean match = myOrders.stream().anyMatch(myOrder->myOrder.getText().equalsIgnoreCase(productName));
		return match;
	}

}
