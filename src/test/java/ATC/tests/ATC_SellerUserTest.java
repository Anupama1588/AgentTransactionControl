package ATC.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ATC.TestComponents.BaseTest;
import ATC.pageObjects.SPQ_FormPage;

public class ATC_SellerUserTest extends BaseTest{
	
	
	
	@Test
	public void seller_login() throws IOException {
		
		
		//--------------------- Page Navigation verification after logged-in ---------------------------------------
		
		lPage.loginToApplication("joseph_seller@yopmail.com","Test@123");
		String expUrl="https://qa-atc.procasaonboard.com/disclosure-progress";
		/*String result=lPage.getLoginVerification();	
		
		if(result.equalsIgnoreCase(expUrl))
			System.out.println("User successfully logged in");	*/
		
		//Thread.sleep(1000);
	}
	
	@Test(dependsOnMethods={"seller_login"})
	public void fillSPQForm() throws IOException, InterruptedException{
		
		SPQ_FormPage spqObj = new SPQ_FormPage(driver);
		String expectedText="Looks like you’ve got things going!";
		
		spqObj.startSPQ(expectedText);
		System.out.println("Terms and conditions are accepted by Seller!");
		Thread.sleep(500);
		
		try {
			spqObj.setAnswerSPQ();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			getScreenshot("fillSPQForm",driver);
			e.printStackTrace();
		}		
		
		
	}
	
	//TDS Form
	
	
	// SPQ Form
	
	
	
	
	
	
	
	
	
	

}
