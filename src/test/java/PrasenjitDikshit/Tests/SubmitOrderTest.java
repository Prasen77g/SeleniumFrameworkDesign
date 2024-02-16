package PrasenjitDikshit.Tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import PrasenjitDikshit.TestComponents.BaseTest;
import PrasenjitDikshit.pageobjects.CartPage;
import PrasenjitDikshit.pageobjects.OrderPage;
import PrasenjitDikshit.pageobjects.ProductCatalouge;
import PrasenjitDikshit.pageobjects.checkOutPage;
import PrasenjitDikshit.pageobjects.confirmationPage;

public class SubmitOrderTest extends BaseTest{



	//String productname = "ZARA COAT 3";
	String CountryName = "India";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		
		//Landing Page & ProductCatalouge Page:-
		ProductCatalouge productCatalouge = landingpage.loginApplication(input.get("email"),input.get("password"));
		productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("product"));
		
		//Cart Page:-
		CartPage cartpage = productCatalouge.goToCartPage();
		boolean match = cartpage.VerifyproductDisplay(input.get("product"));
		AssertJUnit.assertTrue(match);
		
		//CheckOut Page:-
		checkOutPage checkoutpage = cartpage.GotoCheckout();
		checkoutpage.selectcountry(CountryName);
		
		//Confirmation Page:-
		confirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		//String productname = "ZARA COAT 3";
		ProductCatalouge productCatalouge = landingpage.loginApplication("rajdixit2024@gmail.com", "Diamond1996@");
		OrderPage orderPages = productCatalouge.goToOrderPage();
		Assert.assertTrue(orderPages.VerifyorderDisplay("IPHONE 13 PRO"));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//Get Data By using only HashMap
		/*HashMap<String,String> map = new HashMap<String, String>();
		map.put("email", "rajdixit2024@gmail.com");
		map.put("password", "Diamond1996@");
		map.put("product", "ZARA COAT 3");
		HashMap<String,String> map1 = new HashMap<String, String>();
		map1.put("email", "rajad@gmail.com");
		map1.put("password", "Rajad1996@");
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};*/
		
		//Data Provided from JSON file:-
		List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\chumk\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\PrasenjitDikshit\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
