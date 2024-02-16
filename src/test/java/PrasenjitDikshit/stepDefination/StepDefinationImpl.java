package PrasenjitDikshit.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import PrasenjitDikshit.TestComponents.BaseTest;
import PrasenjitDikshit.pageobjects.CartPage;
import PrasenjitDikshit.pageobjects.LandingPage;
import PrasenjitDikshit.pageobjects.ProductCatalouge;
import PrasenjitDikshit.pageobjects.checkOutPage;
import PrasenjitDikshit.pageobjects.confirmationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public confirmationPage confirmationpage;
	public checkOutPage checkoutpage;
	public CartPage cartpage;
	String confirmMessage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingpage = launchApplication();
	}
	@Given("Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password){
		productCatalouge = landingpage.loginApplication(username,password);
	}
	@When("I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products =productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName); 
	}
	@And("Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		
				cartpage = productCatalouge.goToCartPage();
				boolean match = cartpage.VerifyproductDisplay(productName);
				AssertJUnit.assertTrue(match);
				checkoutpage = cartpage.GotoCheckout();
				checkoutpage.selectcountry("India");
				confirmationpage = checkoutpage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmation_page(String string) {
		confirmMessage = confirmationpage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
    public void something_message_is_displayed(String strArg) throws Throwable {
   
		Assert.assertEquals(strArg, landingpage.getErrorMessage());
    	driver.close();
    }

}
