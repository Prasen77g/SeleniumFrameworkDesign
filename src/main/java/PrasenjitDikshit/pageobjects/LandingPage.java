package PrasenjitDikshit.pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import PrasenjitDikshit.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory
	@FindBy(id = "userEmail")
	WebElement Useremail;
	@FindBy(id = "userPassword")
	WebElement Passwordele;
	@FindBy(id = "login")
	WebElement Submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalouge loginApplication(String email, String password) {
		Useremail.sendKeys(email);
		Passwordele.sendKeys(password);
		Submit.click();
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		return productCatalouge;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessage()
	{
		waitforWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
