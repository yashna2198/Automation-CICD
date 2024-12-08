package learning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.abstractcomponents.AbstractComponents;

public class Landingpage extends AbstractComponents{
	
	WebDriver  driver;
		
	public Landingpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail") 
		WebElement email;
	@FindBy(id = "userPassword") 
		WebElement password;
	@FindBy(id = "login") 
		WebElement submit;
	@FindBy(css = "div[class*='flyInOut']") 
	WebElement errormessage;
	
	public Productcatalogue loginCred(String userEmail, String userPassword) {
		
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		submit.click();
		return new Productcatalogue(driver) ;
		
	}
	
	public String errorMessage() {
		return errormessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
