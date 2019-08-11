package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestBase.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath = "//*[@id='undefined-sticky-wrapper']//img")
	WebElement logo;
	
	@FindBy(xpath = "(//input[@id='q'])[2]")
	WebElement txtSearch;
	
	@FindBy(xpath = "//button[@type='submit' and @class='btn btn-orange']")
	WebElement btnSearch;
	
	@FindBy(xpath = "(//*[@id=\'forecast_list_ul\']//a)[1]")
	WebElement result;
	
	@FindBy(xpath = "//div[text()='Not found']")
	WebElement errorResult;
	
	public HomePage () {
		PageFactory.initElements(driver, this);
		
	}
	
	public String fetchWindowTitle() {
		return driver.getTitle();
	}
	
	public boolean verifySearchBox() {
		return driver.findElement(By.xpath("(//input[@id='q'])[2]")).isDisplayed();
	}
	
	public boolean verifySearchButton() {
		return driver.findElement(By.xpath("//button[@type='submit' and @class='btn btn-orange']")).isDisplayed();
	}
	
	public void searchCity(String city) {
		driver.findElement(By.xpath("(//input[@id='q'])[2]")).sendKeys(city);
	}
	
	public void clickSearch() {
		driver.findElement(By.xpath("//button[@type='submit' and @class='btn btn-orange']")).click();
	}
	
	public boolean errorResult(String error) {
		return driver.findElement(By.xpath("//div[text()='Not found']")).getText().contains(error);
	}
	
	public boolean verifyResult(String city) {
		return driver.findElement(By.xpath("(//*[@id=\'forecast_list_ul\']//a)[1]")).getText().contains(city);
	}
	
	public void closeApplication() {
		driver.quit();
	}
	
}
