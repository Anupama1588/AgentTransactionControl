package ATC.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ATC.AbstractComponents.AbstractComponent;

public class TransactionDetailsPage extends AbstractComponent{

	WebDriver tdDriver;
	
	public TransactionDetailsPage(WebDriver driver) {
	
		super(driver);

		//Initialization
		this.tdDriver=driver;		
		PageFactory.initElements(driver,this);
	}
	
	//---------------- Page Factory---------------------------
	@FindBy(xpath="//button[text()='View Details']") //button[@class='text-[#0000FE] border-b border-[#0000FE] cursor-pointer']
	WebElement viewDtlButt;		
	
	@FindBy(xpath="//button[text()=' + Add Projected On Market Date ']")
	WebElement addProjMarkDate;	
	
	@FindBy(xpath="//span[@class='font-medium text-[20px] text-black']")
	WebElement viewTransDialogue;
	
	@FindBy(xpath="//div[@class='mr-2']")  
	WebElement transName;
	
	@FindBy(xpath="//input[@aria-label='Datepicker input']")
	WebElement marketDate;
	
	@FindBy(xpath="//div[@class='dp__menu_inner']")
	WebElement calendorEle;
	
	@FindBy(xpath="//div[@class='dp__cell_inner dp__pointer dp__today dp__date_hover']")
	WebElement todayDate;
	
	@FindBy(xpath="//button[normalize-space()='Select']")
	WebElement calendorSelect;
	
	@FindBy(xpath="//div[@class='font-400 w-[260px] text-[14px] text-right mr-3 mb-3']")
	WebElement marketDateDisp;
	
	@FindBy(css="button[type='submit']")
	WebElement saveButt;
	
	@FindBy(xpath="//button[text()=' Send Email to Seller ']")
	WebElement emailToSeller;
	
	
	@FindBy(id="ccEmail")
	WebElement ccEmail;
	
	@FindBy(xpath="//button[text()=' Send Email ']")
	WebElement sendEmailButt;
	
	@FindBy(xpath="//button[text()=' Add ']")
	WebElement addCCButt;
	
	@FindBy(xpath="//button/img[@alt='close']")
	WebElement close;
	
	
	//---------------  Actions ------------------------------
	public String getTransactionDetails(String expText) throws InterruptedException {
		
		String transactionName=transName.getText();
		
		Thread.sleep(500);
		viewDtlButt.click();
		
		Boolean match=viewTransDialogue.getText().equalsIgnoreCase(expText);
		if(match) {
			System.out.println("Transaction =" + transactionName);
			Thread.sleep(500);
			close.click();
		}
		else
			System.out.println("Transanction dialogue not gets opened");			
		
		return transactionName;
	}
	
	public void editTransaction(String transactionName) throws InterruptedException {
				
		Thread.sleep(500);
		addProjMarkDate.click();
		
		//Add/Update ProjectedOnMarketDate
		marketDate.click();
		waitForWebElementToAppear(calendorEle);
		
		todayDate.click();
		calendorSelect.click();
		
		Thread.sleep(500);
		saveButt.click();
		
		String displayedMarketDate=marketDateDisp.getText();
		System.out.println(displayedMarketDate);
		
		
	}
	
	public void sendEmailToSeller(String ccEmailID) throws InterruptedException {
		
		waitForWebElementToAppear(emailToSeller);
		emailToSeller.click();
		
		waitForWebElementToAppear(ccEmail);
		//Add cc email
		ccEmail.sendKeys(ccEmailID);
		addCCButt.click();
		
		Thread.sleep(500);
		sendEmailButt.click();
		
	}
	
	
	

}
