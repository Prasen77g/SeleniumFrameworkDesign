package PrasenjitDikshit.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PrasenjitDikshit.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;
	//WebDriver driver= new ChromeDriver();
	public LandingPage landingpage;
	

	public WebDriver initializeDriver() throws IOException {

		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\chumk\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\PrasenjitDikshit\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
			//options.addArguments("--window-size=1280,700");
			options.addArguments("--headless=new");
				//options.addArguments("headless");
			}		
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		} else if (browserName.contains("firefox")) {
			// firefox
			WebDriverManager.firefoxdriver().setup();
			/*System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\chumk\\geckodriver-v0.34.0-win64\\geckodriver.exe");*/
			driver = new FirefoxDriver();
		}

		else if (browserName.contains("edge")) {
			// edge
			
			WebDriverManager.edgedriver().setup();
			/*System.setProperty("webdriver.edge.driver",
					"C:\\Users\\chumk\\edgedriver_win64\\msedgedriver.exe");*/
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	//get data from json file:-
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	//Taking Screenshots:-
		public String getScreenShot(String TestCaseName,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + TestCaseName + ".png"	);	
			FileUtils.copyFile(source,file); 
			return System.getProperty("user.dir") + "//reports//" + TestCaseName + ".png"	;
		}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
	}

}
