package ATC.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ATC.TestComponents.BaseTest;
import ATC.pageObjects.NewListingPage;
import ATC.pageObjects.TransactionDetailsPage;

public class NewListingTest extends BaseTest{

	NewListingPage newLstPage;
	TransactionDetailsPage tranDtlPage;
	
	
	@Test
	public void agent_login() throws IOException, InterruptedException {


		//--------------------- Page Navigation verification after logged-in ---------------------------------------

		//lPage.loginToApplication("bosco_agent@yopmail.com","Test@123");
		lPage.loginToApplication("eddy_agent@yopmail.com","Sanupama@15");
		
		Thread.sleep(500);
		
		String expUrl="https://qa-atc.procasaonboard.com/transaction-overview";
		String result=lPage.getLoginVerification();	

		if(result.equalsIgnoreCase(expUrl))
			System.out.println("User successfully logged in");	

		Thread.sleep(1000);
	}

	@Test(dependsOnMethods={"agent_login"})
	public void newListingTransaction() throws InterruptedException
	{
		//-------------------------- Add New Transaction------------------------------------------------------------
		newLstPage=new NewListingPage(driver);

		newLstPage.launchNewTransaction();
		newLstPage.selectNewListingTransaction();

		String propertyLocation="Redlands CA, USA";
		String propertyType = "Residential 5+";
		String clientType="Non-Entity Individual";

		newLstPage.addNewListingTransaction(propertyLocation, propertyType, clientType);

	/*	//send email to seller
		tranDtlPage=new TransactionDetailsPage(driver);
		String ccUserEmailID="sanupama@valueaddsofttech.com";
		tranDtlPage.sendEmailToSeller(ccUserEmailID);*/
	}

	/*@Test
	public void verifyAddedNewListingTransaction() {

		newLstPage=new NewListingPage(driver); 
		String transOverviewUrl=newLstPage.getPageAfterTransactionCreation();
		System.out.println(transOverviewUrl);
	}

	@Test
	public void invitationEmailToSeller() throws InterruptedException {
		TransactionDetailsPage tranDtlPage=new TransactionDetailsPage(driver);

		String ccUserEmailID="sanupama@valueaddsofttech.com";
		tranDtlPage.sendEmailToSeller(ccUserEmailID);

	}*/



}
