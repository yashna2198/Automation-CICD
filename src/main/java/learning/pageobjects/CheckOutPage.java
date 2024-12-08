package learning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.abstractcomponents.AbstractComponents;

public class CheckOutPage  extends AbstractComponents{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[placeholder = 'Select Country']" )
		WebElement countryname;
	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]" )
		WebElement selectCountry;
	@FindBy(css = ".action__submit")
		WebElement confirmOrder;
	
	By countryresult = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(countryname, countryName).build().perform();
		waitUntilElementAppear(countryresult);
		selectCountry.click();
	}
	
	public ConfirmationPage confirmOrderButton() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.body.style.zoom = '80%'");		                                                       
		confirmOrder.click();
		return new ConfirmationPage(driver);
	}
}
