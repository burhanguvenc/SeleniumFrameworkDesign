package tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import bgacademy.pageobjects.CartPage;
import bgacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups={"ErrorHandling"}, retryAnalyzer= TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
			@SuppressWarnings("unused")
			String productName = "ZARA COAT 3";
			landingPage.loginApplication("anshika@gmail.con", "Iamkin000");
			Assert.assertEquals("Incorrect email password", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

			String productName = "ZARA COAT 3";
			ProductCatalogue productCatalogue =landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");

			@SuppressWarnings("unused")
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage =productCatalogue.goToCartPage();

			Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
		}
}
