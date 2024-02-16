package PrasenjitDikshit.AbstractComponents;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PrasenjitDikshit.pageobjects.CartPage;
import PrasenjitDikshit.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "button[routerlink*='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css = "button[routerlink*='/dashboard/myorders']")
	WebElement orderHeader;
	
	@FindBy(css = ".ng-animating")
	WebElement ele;

	public void waitforElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitforWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitforElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;

	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;

	}
	

}
