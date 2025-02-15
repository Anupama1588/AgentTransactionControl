package ATC.TestComponents;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ATC.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage lPage;
	Properties prop;

	public WebDriver initializationDriver() throws IOException {

		//access global properties
		// properties class

		prop=new Properties();

		//FileInputStream fis=new FileInputStream("D:\\Personal\\Automation\\Eclipse_WorkPlace\\ATCAutomationProject\\src\\main\\java\\ATC\\resources\\GlobalData.properties");
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//ATC//resources//GlobalData.properties");

		prop.load(fis);

		String browserName=prop.getProperty("browser");

		// Broswer
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")){
			// Edge browser setup

		}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "D:/Anupama Work/Anupama_work/Automation_SelJava/geckodriver.exe");
			driver=new FirefoxDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String testName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		//File targetFile=new File(System.getProperty("user.dir")+"//reports//"+testName+".png");
		File targetFile=new File(System.getProperty("user.dir")+"//reports//"+testName+System.currentTimeMillis()+".png");
		FileUtils.copyFile(source, targetFile);

			
		return System.getProperty("user.dir")+"//reports//"+testName+".png";
	}


	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {

		// Read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to Hashmap -> jackson databind
		ObjectMapper mapper=new ObjectMapper();

		List<HashMap<String, String>> data= mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){			
		});

		return data;			
	}

	@BeforeTest(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {

		driver=initializationDriver();

		lPage=new LoginPage(driver);
		lPage.goTo();
		return lPage;
	}

	/*@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}*/

    public void selectDate(WebElement datePicker) {
    	
    }
    
}
