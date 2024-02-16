package PrasenjitDikshit.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import PrasenjitDikshit.AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {
	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	
	//private By shownelement = By.cssSelector(".ta-results");

	public void selectcountry(String CountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitforElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}

	public confirmationPage submitOrder() {
		Actions actions = new Actions(driver);
		actions.moveToElement(placeOrder).click().build().perform();
		return new confirmationPage(driver);
	}

}

