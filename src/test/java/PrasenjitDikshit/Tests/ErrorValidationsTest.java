package PrasenjitDikshit.Tests;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

import PrasenjitDikshit.TestComponents.BaseTest;
import PrasenjitDikshit.TestComponents.Retry;
import PrasenjitDikshit.pageobjects.CartPage;
import PrasenjitDikshit.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
	
		landingpage.loginApplication("anshika@gmail.com" , "1996@");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productname = "ZARA COAT 3";
		ProductCatalouge productCatalouge = landingpage.loginApplication("rajdixit2024@gmail.com", "Diamond1996@");
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productname);
		CartPage cartpage = productCatalouge.goToCartPage();
		Boolean match = cartpage.VerifyproductDisplay("ZARA COAT 333");
		Assert.assertFalse(match);
    }
	
	
}
