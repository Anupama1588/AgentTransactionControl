package ATC.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ATC.TestComponents.BaseTest;
import ATC.pageObjects.NewListingPage;
import ATC.pageObjects.TransactionDetailsPage;

public class TransactionDetailsTest extends BaseTest{
	
	TransactionDetailsPage tranDtlPage;
	String propertyName;
	 
	@Test
	public void agent_login() throws IOException, InterruptedException {
		
		
		//--------------------- Page Navigation verification after logged-in ---------------------------------------
		
		lPage.loginToApplication("bosco_agent@yopmail.com","Test@123");
		String expUrl="https://qa-atc.valueaddsofttech.com/transaction-overview";
		String result=lPage.getLoginVerification();	
		
		System.out.println("//------------Execution begins ----------------");	
		
		if(result.equalsIgnoreCase(expUrl))
			System.out.println("User successfully logged in");	
		
		Thread.sleep(500);
	}
	
	@Test
	public void transactionDetails() throws InterruptedException
	{
		tranDtlPage=new TransactionDetailsPage(driver);
		
		String transactionDetailText="Details Of Transaction";
		propertyName = tranDtlPage.getTransactionDetails(transactionDetailText);
				
		/*String ccUserEmailID="sanupama@valueaddsofttech.com";
		tranDtlPage.sendEmailToSeller(ccUserEmailID);*/
		
	}
	
	@Test(dependsOnMethods="agent_login")
	public void addMarketDate() throws InterruptedException {
		tranDtlPage=new TransactionDetailsPage(driver);
		tranDtlPage.editTransaction(propertyName);
		
		System.out.println("//------------Execution Ends ----------------");		
		
	}

}
