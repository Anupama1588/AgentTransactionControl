package ATC.pageObjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ATC.AbstractComponents.AbstractComponent;

public class NewListingPage extends AbstractComponent {

	WebDriver ldriver;

	public NewListingPage(WebDriver driver) {

		super(driver);

		//Initialization
		this.ldriver=driver;		
		PageFactory.initElements(driver,this);
	}

	//------------------ PageFactory -----------------------------------------------

	@FindBy (xpath="//button[text()=' + New Transaction ']")
	WebElement newTransButt;

	@FindBy(xpath="//input[@type='radio'and @value='new_listing']")
	WebElement newListingTranRB;

	@FindBy(xpath="//button[@type='submit']")
	WebElement submit;

	@FindBy(id="view_detail_close")
	WebElement close;

	@FindBy(xpath="(//*[@placeholder='Enter Property Location'])[1]")
	WebElement propertyLocation;

	@FindBy(xpath="//ul[@class='suggestions-dropdown p-2']/li/span[text()=' Recommended']")
	WebElement recommendBox;

	@FindBy(xpath="//li[@class='suggestion-item px-[8px] py-[2px]']")
	WebElement recommendLoc;

	@FindBy(id="transactionName")
	WebElement transactionName;

	@FindBy(id="propertyType")
	WebElement propertyType;

	@FindBy(id="clientType")
	WebElement clientType;

	@FindBy(id="nrdsNo")
	WebElement nrdNO;

	@FindBy(id="carLastName")
	WebElement carLastName;

	@FindBy(xpath="//button[text()=' Validate ']")
	WebElement validateButt;

	@FindBy(xpath="//span[@class='text-green-500 text-sm mt-1']")
	WebElement validateText;

	@FindBy(id="seller.fullName")
	WebElement sellerName;

	@FindBy(id="seller.email")
	WebElement sellerEmail;

	@FindBy(xpath="div[class='search-btn'] svg")
	WebElement search;

	@FindBy(xpath="//button[text()=' - ']")
	WebElement removeSeller;

	@FindBy(xpath="//div[@data-testid='toast-content']")
	WebElement successMsg;

	By newTransactionDialogue=By.xpath("//*[@class='w-full md:max-w-2xl 2xl:max-w-3xl p-0 bg-white rounded-md']");

	// ------------------ Actions -----------------------------------------------
	public void launchNewTransaction() {		
		newTransButt.click();
	}

	public void selectNewListingTransaction() throws InterruptedException {

		//waitForElementToAppear(newTransactionDialogue);
		newListingTranRB.click();		
	}

	public void addNewListingTransaction(String location, String type, String clientTP) throws InterruptedException {

		JavascriptExecutor js=(JavascriptExecutor)ldriver;

		//Property Location
		System.out.println("New Listing Transaction --> ");
		propertyLocation.sendKeys(location);
		propertyLocation.sendKeys(Keys.ENTER);

		waitForWebElementToAppear(recommendBox);
		recommendLoc.click();

		@SuppressWarnings("deprecation")
		String PropertyName=transactionName.getAttribute("value");
		System.out.println("Property Name = " + PropertyName);

		//Property Type
		Select selProp = new Select(propertyType);
		selProp.selectByValue(type);
		System.out.println("Selected Property Type = " + type);

		//Client Type
		Select selClient = new Select(clientType);
		selClient.selectByValue(clientTP);
		System.out.println("Selected Client Type = " + clientTP);

		//NRDS details
		js.executeScript("arguments[0].scrollIntoView(true);", validateButt);

		nrdNO.sendKeys("183506867");
		carLastName.sendKeys("Moran");
		validateButt.click(); 

		//------------------------------------------------

		Thread.sleep(1000);
		
		Set <String> windows = ldriver.getWindowHandles();
		for (String handle: windows)
		{
			System.out.println("Windows : " + handle);
			
		}
				
		String pagetitle = ldriver.getTitle();
		System.out.println("Current Page: " + pagetitle);
			
		
		if(pagetitle.equalsIgnoreCase("CALIFORNIA ASSOCIATION OF REALTORS® - www.car.org  "))
		{
			ldriver.close();
			System.out.println("Closed the  '"+pagetitle+"' Tab now ...");
		}
		
			

		//-----------------------------------------------------


		String validText=validateText.getText();
		System.out.println(validText);

		if(validText.equalsIgnoreCase("Validation is successful.")) {

			//Seller details
			sellerName.sendKeys("Joseph S");
			sellerEmail.sendKeys("joseph_seller@yopmail.com");

			js.executeScript("arguments[0].scrollIntoView(true);", submit);
			Thread.sleep(500); 

			//Submit
			//submit.click();	
		}
		else
			System.out.println("CAR validation is failed...");

	}

	public String getPageAfterTransactionCreation() {

		waitForWebElementToAppear(successMsg);

		System.out.println(successMsg.getText());

		String url=ldriver.getCurrentUrl();
		return url;			
	}

	//div[@data-testid='toast-content']

}
