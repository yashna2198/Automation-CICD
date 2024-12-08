package learning;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import learning.TestComponents.BaseTest;
import learning.TestComponents.Retry;
import learning.pageobjects.CartPage;
import learning.pageobjects.Productcatalogue;

public class ErrorValidationTest extends BaseTest{
	
	@Test (groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() {
		
		String mail = "arjun2103@gmail.com";
		String password = "Arjun98@2103" ;
		
		landingpage.loginCred(mail,password);
		Assert.assertEquals("Incorrect email or password.", landingpage.errorMessage());
	}
	
	@Test
	public void productErrorValidation() {

		String productName = "IPHONE 13 PRO";
		String mail = "karan2103@gmail.com";
		String password = "Karan@2103" ;
		
		Productcatalogue productcatalogue = landingpage.loginCred(mail,password);
		List<WebElement> items = productcatalogue.getProducts();
		CartPage cartpage = productcatalogue.SelectToCart(items, productName);		
		productcatalogue.goToCart();	
		boolean match = cartpage.verifyItems("IPHONE 13 PRO");
		Assert.assertTrue(match);
	}

}
