package PrasenjitDikshit.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PrasenjitDikshit.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String productname = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		// LandingPage landingpage =
		new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("rajdixit2024@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Diamond1996@");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartproducts.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build()
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ta-results']")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		WebElement element = driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		String confirmMessage =
		driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
