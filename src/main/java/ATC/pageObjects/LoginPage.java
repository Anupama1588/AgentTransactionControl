package ATC.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ATC.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		//sending driver to parent class
		super(driver);
		//Initialization
		this.driver=driver;

		PageFactory.initElements(driver,this);
	}


	//------------- Page FActory ---------------------

	@FindBy(id="my_email")
	WebElement userName;

	@FindBy(xpath="//input[@name='password']")	
	WebElement passwordEle;

	@FindBy(xpath="//img[@class= 'mr-2']")
	WebElement logoutButt;

	@FindBy(xpath="//div[@data-testid='toast-content']")
	WebElement errorMsg;

	By menuList=By.xpath("//h3[text()='Transaction Overview'] ");

	//----------------- Action------------------------

	public void goTo(){ 
		driver.get("https://qa-atc.procasaonboard.com/login");
	}

	public void loginToApplication(String email, String password){

		//waitForWebElementToAppear(userName);
		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		passwordEle.sendKeys(Keys.RETURN);
	}

	public String getLoginVerification() {

		waitForElementToAppear(menuList);

		String url=driver.getCurrentUrl();
		return url;					
	}

	public String getLoginErrorMessage() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();

	}

	public void logoutFromApplication() {

		logoutButt.click();
	}

}
