package learning.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import learning.pageobjects.Landingpage;

public class BaseTest {
	
	public WebDriver driver;
	public Landingpage landingpage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\learning\\resources\\globaldata.properties");
		props.load(fis);
		
		String Browser = System.getProperty("browser")!=null? System.getProperty("browser") : props.getProperty("browser");
				
		if(Browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(Browser.contains("headless"))
				options.addArguments("headless");
			driver = new ChromeDriver(options);
		}
		
		else if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		else if (Browser.equalsIgnoreCase("microsoft edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void LaunchWebsite() throws IOException {
		
		driver = initializeDriver();
		landingpage = new Landingpage(driver);
		landingpage.goTo();
	}
	
	@AfterMethod(alwaysRun = true)
	public void quit() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> DataGetterFromJson(String filePath) throws IOException {
		// Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to HashMap using Jackson databind with ObjectMapper class
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
		
	}
	
	@DataProvider
	public Object[][] getArrayData() {
		return new Object[][] {{"arjun2103@gmail.com","Arjun@2103","IPHONE 13 PRO"} , 
			{"karan2103@gmail.com","Karan@2103","ZARA COAT 3"}};
	}
	
	@DataProvider
	public Object[][] getHashMapData() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\learning\\data\\PurchaseOrder.json";
		List<HashMap<String,String>> data = DataGetterFromJson(filePath);
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		return new Object[][] { {data.get(0)},{data.get(1)} };
		
/*		HashMap<String,String> set1 = new HashMap<String,String>();
		set1.put("mail", "arjun2103@gmail.com");
		set1.put("password", "Arjun@2103");
		set1.put("productName", "IPHONE 13 PRO");
		
		HashMap<String,String> set2 = new HashMap<String,String>();
		set2.put("mail", "karan2103@gmail.com");
		set2.put("password", "Karan@2103");
		set2.put("productName", "ZARA COAT 3");
		
		return new Object[][] {{set1},{set2}};    */
		
	}
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+".png"));
		return System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+".png";
		
	}
}
