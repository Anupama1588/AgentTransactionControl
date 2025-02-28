package ATC.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ATC.TestComponents.BaseTest;
import ATC.pageObjects.MenuItem;
import ATC.pageObjects.NewListingPage;

public class ATC_AgentUserTest extends BaseTest{
	
	@Test
	public void agent_login() throws IOException {
		
		
		//--------------------- Page Navigation verification after logged-in ---------------------------------------
		
		lPage.loginToApplication("bosco_agent@yopmail.com","Test@123");
		String expUrl="https://qa-atc.procasaonboard.com/transaction-overview";
		String result=lPage.getLoginVerification();	
		
		if(result.equalsIgnoreCase(expUrl))
			System.out.println("User successfully logged in");	
		
		//Thread.sleep(1000);
	}
	
	@Test
	public void newTransaction() throws InterruptedException
	{
		//-------------------------- Add New Transaction------------------------------------------------------------
		 NewListingPage newTransPage=new NewListingPage(driver);
		
		newTransPage.launchNewTransaction();
		newTransPage.selectNewListingTransaction();
		
		//Close New Transaction dialogue
		driver.findElement(By.id("view_detail_close")).click();

	}
	
	@Test(dependsOnMethods={"agent_login"})
	public void agent_action() 
	{
		//---------------------------- Agent User Menu List --------------------------------------------------------
		
			
		MenuItem menuListPage=new MenuItem(driver);
		List <WebElement> menuLists=menuListPage.getMenuList();
		
		menuListPage.displayMenuItem(menuLists);
				
		//Check menuitem 
		
		Boolean match=menuListPage.checkMenuItem("All Transactions");				
		Assert.assertTrue(match);
			
		//View Details
		//driver.findElement(By.xpath("//button[text()='View Details']")).click();
	}
	
	@Test(dependsOnMethods={"agent_login"})
	public void logoutUser() {
		lPage.logoutFromApplication();
	}
	
		
	
	
	
	
	
	
	
	

}
