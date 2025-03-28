package ATC.pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ATC.AbstractComponents.AbstractComponent;

public class TDS_FormPage extends AbstractComponent{

	WebDriver tdsDriver;
	JavascriptExecutor js;

	public TDS_FormPage(WebDriver driver) {

		super(driver);

		//Initialization
		this.tdsDriver=driver;		
		PageFactory.initElements(driver,this);
		js=(JavascriptExecutor)tdsDriver;

	}

	//---------------- Page Factory---------------------------
	@FindBy(xpath="(//button[text()=' Get Started '])[4]") 
	WebElement getStartedLatest;		

	@FindBy(xpath="(//*[@class='cursor-pointer'])[1]")
	WebElement tdsCursor;

	@FindBy(xpath="//button[text()=' Back ']")
	WebElement transBackButt;

	@FindBy(xpath="//input[@name=\"propertyType\"and @value=\"No\"]")
	WebElement propTypeNo;		

	@FindBy(xpath="//button[text()=\" Let’s Get Started! \"]")
	WebElement getStartedTDS;		

	@FindBy(id="limitationAccepted")
	WebElement termCondChkBx;

	@FindBy(xpath="//button[text()=' Continue ']")
	WebElement contiNueButt;

	@FindBy(xpath="//button[@title='Sub-Sections']")
	WebElement subSecButt;

	@FindBy(xpath="//button[contains(@class,\"text-[#0000FE] bg-[#F0F0FF]\")]")
	WebElement currentSec;

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

	@FindBy(xpath="//input[@type='checkbox'and @value='I-1']")
	WebElement FstQue;

	@FindBy(xpath="//input[@name='I_text']")
	WebElement FstQueExpl;

	//Section 2 web elements
	@FindBy(xpath="//input[@name=\"PropertyItemsChecklist\" and contains(@value,\"2\")]")
	WebElement propItem1;

	@FindBy(xpath="//input[@name=\"PropertyItemsChecklist\" and contains(@value,\"47\")]")
	WebElement propItem2;

	@FindBy(xpath="//input[@name='age_text']")
	WebElement ageText;

	@FindBy(xpath="(//input[@value='No'])[2]")
	WebElement buttNO;

	//Section 3 web elements
	@FindBy(xpath="//input[@name='B1_Yes']")
	List<WebElement> defectItemLst;

	@FindBy(id="undefined__describe")
	WebElement descrbe;

	@FindBy(id="undefined__explanation")
	WebElement explanation;



	@FindBy(xpath="//button[text()=' Done ']")
	WebElement doneButt;

	@FindBy(xpath="//form[@method='dialog']/h3[text()='Review Form']")
	WebElement confirmDlg;

	@FindBy(xpath="//form[@method='dialog']/button[text()=' Yes ']")
	WebElement confirmYesButt;


	//---------------  Actions ------------------------------

	public void startTDS() throws InterruptedException {

		js.executeScript("arguments[0].scrollIntoView(true);", getStartedLatest);

		Thread.sleep(500); 
		getStartedLatest.click();

		// TDS Form terms and condition
		waitForWebElementToAppear(transBackButt);

		//TDS number of unit selection

		tdsCursor.click();
		propTypeNo.click();


		waitForElementToBeClickable(getStartedTDS);
		getStartedTDS.click();

		// Get the current section where the user is landed
		String currentSectionName=currentSec.getText().toString();
		System.out.println(currentSectionName);
		System.out.println("---------------------");

		//Terms and Conditions
		if(currentSectionName.equalsIgnoreCase("PROPERTY INFORMATION")) {
			if ( !termCondChkBx.isSelected() )
				termCondChkBx.click();

			waitForElementToBeClickable(contiNueButt);
			contiNueButt.click();
			sec1_answerTDS();
		}
		else if(currentSectionName.equalsIgnoreCase("I. COORDINATION WITH OTHER DIS...")) {
			sec1_answerTDS();
		}
		else if(currentSectionName.equalsIgnoreCase("II. A SELLER’S INFORMATION")) {
			sec2A_answerTDS();
		}
		else if(currentSectionName.equalsIgnoreCase("II. B DEFECTS/MALFUNCTIONS")) {
			sec2B_answerTDS();
		}
		else if(currentSectionName.equalsIgnoreCase("II. C PROPERTY ISSUES")) {
			sec2C_answerTDS();
		}
		else
			reviewTDS();
	}

	public void sec1_answerTDS() {

		waitForWebElementToAppear(saveNextButt);		

		if ( !FstQue.isSelected() ) {
			FstQue.click();

			waitForWebElementToAppear(FstQueExpl);		
			FstQueExpl.sendKeys("--------- Dummey text----------");			 
		}		 
		saveNextButt.click();
		sec2A_answerTDS();		
	}


	public void sec2A_answerTDS() {

		waitForWebElementToAppear(saveNextButt);

		radioButtYes.click();
		propItem1.click();

		js.executeScript("arguments[0].scrollIntoView(true);", propItem2);
		propItem2.click();

		js.executeScript("arguments[0].scrollIntoView(true);", ageText);
		ageText.sendKeys(" Property Age: 10 Years Old ");
		buttNO.click();

		saveNextButt.click();

		sec2B_answerTDS();
	}

	public void sec2B_answerTDS() {


		waitForWebElementToAppear(saveNextButt);

		js.executeScript("arguments[0].scrollIntoView(true);", radioButtYes);
		radioButtYes.click();

		for(int i=0; i<defectItemLst.size(); i++) {
			int number=(i%3);
			if(number == 0){

				@SuppressWarnings("deprecation")
				String itemNAme=defectItemLst.get(i).getAttribute("value").toString();
				System.out.println(itemNAme);

				defectItemLst.get(i).click();
			}
		}
		
		js.executeScript("arguments[0].scrollIntoView(true);", descrbe);		
		descrbe.sendKeys("Defect: there are cracks in ceilings");
		
		explanation.sendKeys(" Explanation: Dummy text");

		js.executeScript("arguments[0].scrollIntoView(true);", descrbe);
		saveNextButt.click();

		sec2C_answerTDS();

	}


	public void sec2C_answerTDS() {

		int i=0;	
		String questionNo = " ";

		for(i=1; i<=30; i++) {

			try {
				waitForWebElementToAppear(saveNextButt);					
				if(saveNextButt.isDisplayed()) {

					waitForElementToBeClickable(radioButtNO);

					Thread.sleep(1000);
					int number=(i%5);

					// Select option 'Yes'
					if(number == 0)
					{
						radioButtYes.click();

						waitForWebElementToAppear(explanationText);
						explanationText.sendKeys("DUMMY TEXT .........");
					}
					//select option 'No'
					else {
						radioButtNO.click();	
					}

					questionNo= radioButtNO.getAttribute("name").toString();
					System.out.println("iteration : "+ i);  
					System.out.println("Question " + questionNo + " attempted");
	
					
					if(questionNo.equalsIgnoreCase("C16"))
					{

						saveNextButt.click();							

						System.out.println("Congratulations!!!, you have completed TDS seller form");
						System.out.println("-----------------------------------------------------");
						break;
					}			
					
					saveNextButt.click();
					
				}									

			}catch (Exception e) {

				e.printStackTrace();
			}						
		}	

		reviewTDS();

	}


	public void reviewTDS() {

		js.executeScript("arguments[0].scrollIntoView(true);", doneButt);

		doneButt.click();

		waitForWebElementToAppear(confirmDlg);
		confirmYesButt.click();	

	}

}

