package learning.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import learning.pageobjects.orderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
		WebElement cartHeader;
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersHeader;
	
	
	public void goToCart() {
		cartHeader.click();
	}
	
	public orderPage goToMyOrders() {
		ordersHeader.click();
		return new orderPage(driver);
	}
	public void waitUntilElementClickable(By findBy) {	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitUntilElementAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); 
	}
	public void waitvisibilityOfWebElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(element));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitInvisibilityOfWebElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
}
