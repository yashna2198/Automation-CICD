package learning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.abstractcomponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "hero-primary")
		WebElement text;
	
	public String confirmationMessage() {
		return text.getText();
	}
}
