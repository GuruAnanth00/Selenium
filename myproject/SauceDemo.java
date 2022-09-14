package org.myproject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SauceDemo {

	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		System.setProperty("webdriver.chrome.driver", "c:\\workspace\\Selenium\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
		String title = driver.getTitle();
		System.out.println(title);
		
		String currenturl = driver.getCurrentUrl();
		System.out.println(currenturl);
		
		WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
		username.sendKeys( "standard_user"); 
	    WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
	    password.sendKeys("secret_sauce");
	    WebElement button = driver.findElement(By.xpath("//input[@id='login-button']"));
	    button.click();
	    //add to cart
	    WebElement cart = driver.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-backpack\"]"));
		cart.click();
	    
        Actions a=new Actions(driver);
         WebElement prd = driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[1]"));
		
		Thread.sleep(3000);
		
		a.contextClick(prd).perform();
		
		
		Thread.sleep(3000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
    	r.keyRelease(KeyEvent.VK_DOWN);
    	r.keyPress(KeyEvent.VK_ENTER);
    	r.keyRelease(KeyEvent.VK_ENTER);
    	
    	Thread.sleep(3000);
    	
        Set<String> window = driver.getWindowHandles();
        List<String> li=new ArrayList<String>();
		li.addAll(window);
		String each =li.get(1);
		driver.switchTo().window(each);
		
        WebElement prd1 = driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[1]"));
        String text = prd1.getText();
        System.out.println(text);

		WebElement select = driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[1]"));
		a.contextClick(select).perform();
        Thread.sleep(5000);
        WebElement product = driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[6]"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",product);
        TakesScreenshot tk =(TakesScreenshot)driver;
		
		File src = tk.getScreenshotAs(OutputType.FILE);
		
		File des=new File("C:\\workspace\\Selenium\\ScreenShort\\myproject.jpg");
		FileUtils.copyFile(src, des);
     
		
	    
	}   

}
