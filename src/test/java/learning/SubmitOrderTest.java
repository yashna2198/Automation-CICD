package learning;


import learning.TestComponents.BaseTest;
import learning.pageobjects.CartPage;
import learning.pageobjects.CheckOutPage;
import learning.pageobjects.ConfirmationPage;
import learning.pageobjects.Productcatalogue;
import learning.pageobjects.orderPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
	
	@Test(dataProvider ="getArrayData",  groups = {"Purchase"})
	public void OrderSubmit(String mail,String password,String productName) throws IOException {
		
		String countryname = "India";
				
		Productcatalogue productcatalogue = landingpage.loginCred(mail,password);
		List<WebElement> items = productcatalogue.getProducts();
		CartPage cartpage = productcatalogue.SelectToCart(items, productName);
		
		productcatalogue.goToCart();
		
		boolean match = cartpage.verifyItems(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.checkOut();
		
		checkoutpage.selectCountry(countryname);
		ConfirmationPage confirmationpage = checkoutpage.confirmOrderButton();
		
		Assert.assertTrue(confirmationpage.confirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));			
	}
	
	@Test(dataProvider ="getHashMapData",dependsOnMethods = {"OrderSubmit"} , groups = {"Purchase"}) 
	public void checkOrder(HashMap<String,String> input) {
		
		Productcatalogue productcatalogue = landingpage.loginCred(input.get("mail"),input.get("password"));
		orderPage orderpage = productcatalogue.goToMyOrders();
		Assert.assertTrue(orderpage.myLatestOrder(input.get("productName")));;
	}

}
