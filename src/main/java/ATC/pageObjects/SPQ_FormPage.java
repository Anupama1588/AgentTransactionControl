package ATC.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ATC.AbstractComponents.AbstractComponent;

public class SPQ_FormPage extends AbstractComponent{

	WebDriver spqDriver;
	JavascriptExecutor js;

	public SPQ_FormPage(WebDriver driver) {

		super(driver);

		//Initialization
		this.spqDriver=driver;		
		PageFactory.initElements(driver,this);
		js=(JavascriptExecutor)spqDriver;

	}

	//---------------- Page Factory---------------------------
	@FindBy(xpath="(//button[text()=' Get Started '])[3]") 
	WebElement getStartedLatest;		

	@FindBy(xpath="(//span[text()=' Start Here '])[1]")
	WebElement spqStart;

	@FindBy(xpath="(//*[@class='cursor-pointer'])[2]")
	WebElement spqCursor;

	@FindBy(xpath="//button[text()=' Back ']")
	WebElement transBackButt;

	@FindBy(xpath="//div/h2[text()='1 . disclosure Limitation: ']")
	WebElement termsCondPage;

	@FindBy(xpath="//span[@class='font-inter text-xl font-light leading-7 text-left text-black']")
	WebElement startPkgText;

	@FindBy(xpath="//input[@name=\"propertyType\"and @value=\"No\"]")
	WebElement propTypeNo;		

	@FindBy(xpath="//button[text()=\" Let’s Get Started! \"]")
	WebElement getStartedSPQ;		

	@FindBy(id="limitationAccepted")
	WebElement termCondChkBx;

	@FindBy(xpath="//button[text()=' Continue ']")
	WebElement contiNueButt;

	@FindBy(xpath="//button[@title='Sub-Sections']")
	WebElement subSecButt;

	@FindBy(xpath="//span[@class='truncate'and text()='5.Documents']")
	WebElement fifthSec;

	@FindBy(xpath="//span[@class='truncate'and text()='18. OTHER']")
	WebElement eighteenSec;

	@FindBy(xpath="//span[@class='truncate'and text()='1-4']")
	WebElement termCondSec;

	@FindBy(xpath="//button[text()=' Back ']")
	WebElement backButt;		

	@FindBy(xpath="//button[text()=' Save & Next ']")
	WebElement saveNextButt;

	@FindBy(xpath="//input[@value='No']")
	WebElement radioButtNO;
	
	@FindBy(xpath="//input[@value='Yes']")
	WebElement radioButtYes;

	@FindBy(xpath="//textarea[@placeholder='Enter Explanation*']")
	WebElement explanationText;
	
	@FindBy(xpath="//input[@type='checkbox'and @name='19B']")
	WebElement lastQue;

	@FindBy(xpath="//button[text()=' Done ']")
	WebElement doneButt;
	
	@FindBy(xpath="//form[@method='dialog']/h3[text()='Review Form']")
	WebElement confirmDlg;
	
	@FindBy(xpath="//form[@method='dialog']/button[text()=' Yes ']")
	WebElement confirmYesButt;


	//---------------  Actions ------------------------------

	public void startSPQ(String expText) throws InterruptedException {

		js.executeScript("arguments[0].scrollIntoView(true);", getStartedLatest);

		Thread.sleep(500); 
		getStartedLatest.click();

		System.out.println(startPkgText.getText());

		// SPQ Form terms and condition
		waitForWebElementToAppear(transBackButt);

		//SPQ number of unit selection
		//spqStart.click();
		spqCursor.click();
		propTypeNo.click();


		waitForElementToBeClickable(getStartedSPQ);
		getStartedSPQ.click();

		subSecButt.click();
		termCondSec.click();

		js.executeScript("arguments[0].scrollIntoView(true);", termCondChkBx);

		if ( !termCondChkBx.isSelected() )
			termCondChkBx.click();

		waitForElementToBeClickable(contiNueButt);
		contiNueButt.click();

	}

	@SuppressWarnings("deprecation")
	public void setAnswerSPQ() throws InterruptedException {

		String questionNo = " ";
		Thread.sleep(500);

		//js.executeScript("arguments[0].scrollIntoView(true);", eighteenSec);
		//eighteenSec.click();

		for(int i=1; i<=100; i++) {

			try {
				waitForWebElementToAppear(saveNextButt);					
				if(saveNextButt.isDisplayed()) {

					waitForElementToBeClickable(radioButtNO);

					questionNo= radioButtNO.getAttribute("name").toString();
					System.out.println("iteration : "+ i);
					System.out.println("Question " + questionNo + " attempted");

					Thread.sleep(1000);

					// Select option 'Yes'
					if((i/10) == 0)
					{
						radioButtYes.click();
						
						waitForWebElementToAppear(explanationText);
						explanationText.sendKeys("DUMMY TEXT .........");
					}
					//select option 'No'
					else {
						radioButtNO.click();	
					}

					saveNextButt.click();	

					if(questionNo.equalsIgnoreCase("19B"))
					{

						lastQue.click();
						saveNextButt.click();							

						System.out.println("Congratulations!!!, you have completed SPQ seller form");
						break;
					}
				
				}									

			}catch (Exception e) {

				e.printStackTrace();
			}						
		}		
		
		// Reviewed and marked as done
		reviewSPQ();		
		
	}

	public void reviewSPQ() {
		
		js.executeScript("arguments[0].scrollIntoView(true);", doneButt);
		
		doneButt.click();
		
		waitForWebElementToAppear(confirmDlg);
		confirmYesButt.click();	
		
	}

}

