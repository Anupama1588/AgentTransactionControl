package ATC.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ATC.AbstractComponents.AbstractComponent;

public class MenuItem extends AbstractComponent{

	WebDriver driver;

	public MenuItem(WebDriver driver) {
		super(driver);

		this.driver=driver;
		PageFactory.initElements(driver,this);	
	}

	/*@FindBy(xpath="//ul[@class='flex flex-col py-3 space-y-1 mt-1']/li")
	List<WebElement> menuList;*/

	By menuLstBy=By.xpath("//ul[@class='flex flex-col py-3 space-y-1 mt-1']/li");

	public List<WebElement> getMenuList() {
		List <WebElement> menuList=driver.findElements(By.xpath("//ul[@class='flex flex-col py-3 space-y-1 mt-1']/li"));
	
			return menuList;
	}

	public void displayMenuItem(List<WebElement> list) {		
	
		list=getMenuList();
		//print menu item
				System.out.println(list.size());
				System.out.println("/------------------------------");
				for(WebElement menuItem : list) {
					String name=menuItem.getText();
					System.out.println(name);
				}
				System.out.println("/------------------------------");
		
	}
	
	public boolean checkMenuItem(String name) {
		List<WebElement> itemLst=getMenuList();
		Boolean match=itemLst.stream().anyMatch(menuItem->menuItem.getText().equalsIgnoreCase(name));
		
		return match;		

	}






}