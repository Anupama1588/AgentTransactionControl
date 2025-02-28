package ATC.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import ATC.TestComponents.BaseTest;
import ATC.pageObjects.NewListingPage;
import ATC.pageObjects.TransactionDetailsPage;

public class AllTransactionsTest extends BaseTest{
	
	@Test
	public void agent_login() throws IOException, InterruptedException {
		
		
		//--------------------- Page Navigation verification after logged-in ---------------------------------------
		
		lPage.loginToApplication("bosco_agent@yopmail.com","Test@123");
		String expUrl="https://qa-atc.procasaonboard.com/transaction-overview";
		String result=lPage.getLoginVerification();	
		
		System.out.println("//------------Execution begins ----------------");	
		
		if(result.equalsIgnoreCase(expUrl))
			System.out.println("User successfully logged in");	
		
		Thread.sleep(500);
	}
	
	

}
