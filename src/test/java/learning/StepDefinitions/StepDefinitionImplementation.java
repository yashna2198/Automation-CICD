package learning.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learning.TestComponents.BaseTest;
import learning.pageobjects.CartPage;
import learning.pageobjects.CheckOutPage;
import learning.pageobjects.ConfirmationPage;
import learning.pageobjects.Landingpage;
import learning.pageobjects.Productcatalogue;

public class StepDefinitionImplementation extends BaseTest {
	
	Landingpage landingpage;
	Productcatalogue productcatalogue;
	CartPage cartpage;
	CheckOutPage checkoutpage;
	ConfirmationPage confirmationpage;
	
	//Given Landing on the ecommerce website
	@Given("Landing on the ecommerce website")
	public void Landing_on_the_ecommerce_website() throws IOException {
		driver = initializeDriver();
		landingpage = new Landingpage(driver);
		landingpage.goTo();
	}
	
	//Given login to the ecommerce website using username <name> password <password>
	@Given("^login to the ecommerce website using username (.+) password (.+)$")
	public void login_to_the_ecommerce_website_using_username_and_password(String username, String password) {
		productcatalogue = landingpage.loginCred(username,password);
	}
	
	//When select the product <productname>
	@When("^select the product (.+)$")
	public void select_the_product(String productname) {
		List<WebElement> items = productcatalogue.getProducts();
		cartpage = productcatalogue.SelectToCart(items, productname);
	}
	
	//And go to the cart
	@When("go to the cart")
	public void go_to_cart() {
		productcatalogue.goToCart();
	}
	
	//And verify the selected <productname> 
	@When("^verify the selected (.+)$")
	public void verifying_item(String productname) {
		boolean match = cartpage.verifyItems(productname);
		Assert.assertTrue(match);
		checkoutpage = cartpage.checkOut();
	}
	
	//And check the order providing the <countryname>
	@When("^check the order providing the (.+)$")
	public void checkout_the_order(String countryname) {
		checkoutpage.selectCountry(countryname);
		confirmationpage = checkoutpage.confirmOrderButton();
	}
	
	//Then Confirm the message "THANKYOU FOR THE ORDER."
	@Then("Confirm the message {string}")
	public void confirmation_message(String string) {
		Assert.assertTrue(confirmationpage.confirmationMessage().equalsIgnoreCase(string));
		driver.quit();
	}
	
	//"Incorrect email or password." message displays
	@Then("{string} message displays")
	public void error_essage_display(String string) {
		Assert.assertEquals(string, landingpage.errorMessage());
		driver.quit();
	}
}
