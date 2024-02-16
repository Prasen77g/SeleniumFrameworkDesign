package PrasenjitDikshit.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PrasenjitDikshit.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents{
	
	WebDriver driver;
	
	//Creating the constructor for getting driver:-
	public ProductCatalouge(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"))
	
	//Getting all the products:-
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	//Getting all Product lists after waiting for the element to be appear:-
	public List<WebElement> getProductList()
	{
		waitforElementToAppear(productsBy);
		return products;
	}
	
	//Getting the Product Names from the Product Lists:-
	public WebElement getProductByName(String productName)
	{
		
		WebElement prod =  getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	
	//Adding the Products into the cart by using product name:-
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prodN =getProductByName(productName);
		prodN.findElement(addToCart).click();
		waitforElementToAppear(toastMessage);
		waitforElementToDisappear(spinner);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
