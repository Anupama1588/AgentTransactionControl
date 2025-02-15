package ATC.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ATC.AbstractComponents.AbstractComponent;

public class AllTransactionsPage extends AbstractComponent{

	WebDriver tdDriver;
	
	public AllTransactionsPage(WebDriver driver) {
	
		super(driver);

		//Initialization
		this.tdDriver=driver;		
		PageFactory.initElements(driver,this);
	}
	
	//---------------- Page Factory---------------------------
	
	@FindBy(xpath="//a[@href=\"/all-transactions\"]/span")
	WebElement allTransactions;
	
	@FindBy(xpath="//input[@type='text' and @placeholder='Search']")
	WebElement searchBox;
	
	@FindBy(xpath="//label[@title='Filter']")
	WebElement filterIcon;
	
	
	
	//---------------  Actions ------------------------------
	
	
	

}
