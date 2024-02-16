package PrasenjitDikshit.pageobjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import PrasenjitDikshit.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"))
	//Geeting all the cartproducts:-
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartproducts;

	//@FindBy(css = ".totalRow button")
	//WebElement Checkoutele;
	////button[normalize-space()='Checkout']
	//li[class='totalRow'] button[type='button']
	@FindBy(css = "li[class='totalRow'] button")
	WebElement Checkoutele;

	public checkOutPage GotoCheckout() throws InterruptedException {
		
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		actions.moveToElement(Checkoutele).click().build().perform();
		
		//Checkoutele.click();
		return new checkOutPage(driver);
	}

	public Boolean VerifyproductDisplay(String productName) {
		Boolean match = cartproducts.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productName));
		return match;
	}

}
