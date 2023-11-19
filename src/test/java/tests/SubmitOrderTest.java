package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import bgacademy.pageobjects.CartPage;
import bgacademy.pageobjects.CheckoutPage;
import bgacademy.pageobjects.ConfirmationPage;
import bgacademy.pageobjects.OrderPage;
import bgacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		@SuppressWarnings("unused")
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		// Validating added products in the cart
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		// Validate Thank you message
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

//To verify Zara Coat 3 is displaying in orders page
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	//Extent Reports ---

	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	//@DataProvider
	//public Object[][] getData(){
	//	return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	//}

	//	HashMap<String,String> map = new HashMap<String,String>();
	//	map.put("email", "anshika@gmail.com");
	//	map.put("password", "Iamking@000");
	//	map.put("product", "ZARA COAT 3");

	//	HashMap<String,String> map1 = new HashMap<String,String>();
	//	map1.put("email", "shetty@gmail.com");
	//	map1.put("password", "Iamking@000");
	//	map1.put("product", "ADIDAS ORIGINAL");
	}






