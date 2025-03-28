package ATC.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ATC.TestComponents.BaseTest;
import ATC.pageObjects.SPQ_FormPage;
import ATC.pageObjects.TDS_FormPage;

public class ATC_SellerUserTest extends BaseTest{
	
	
	
	@Test
	public void seller_login() throws IOException {
		
		
		//--------------------- Page Navigation verification after logged-in ---------------------------------------
		
		lPage.loginToApplication("joseph_seller@yopmail.com","Test@123");
		//lPage.loginToApplication("liva_seller@yopmail.com","Sanupama@15");
		
		String expUrl="https://qa-atc.procasaonboard.com/disclosure-progress";
		/*String result=lPage.getLoginVerification();	
		
		if(result.equalsIgnoreCase(expUrl))
			System.out.println("User successfully logged in");	*/
		
		//Thread.sleep(1000);
	}

	// SPQ Form
	/*
	@Test(dependsOnMethods={"seller_login"})
	public void SPQ_Form() throws IOException, InterruptedException{
		
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
	}*/
	
	

	// TDS Form
	@Test(dependsOnMethods={"seller_login"})
	public void TDS_Form() throws InterruptedException {
		TDS_FormPage tdsObj=new TDS_FormPage(driver);
		
		tdsObj.startTDS();
		
	}
	
	
		
	
	
	
	
	
	

}
