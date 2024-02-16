package PrasenjitDikshit.pageobjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import PrasenjitDikshit.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"))
	//Geeting all the cartproducts:-
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> orderproducts;


	public Boolean VerifyorderDisplay(String productname) {
		Boolean match = orderproducts.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productname));
		return match;
	}

}
