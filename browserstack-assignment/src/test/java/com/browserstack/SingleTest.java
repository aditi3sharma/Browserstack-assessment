package com.browserstack;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
    	JavascriptExecutor jse = (JavascriptExecutor)driver;

        // Setting name of the test
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"Mobile List test \" }}");
        driver.manage().window().maximize();
        
        //Load the flipkart home page
    	driver.get("https://www.flipkart.com/");

		// Wait till login pop up appears, once visible clicking on close
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
		wait.until(ExpectedConditions.elementToBeClickable(By.className("_2doB4z"))).click();
		
		// Selecting search bar and searching for 'Samsung Galaxy S10'
		WebElement searchBar = driver.findElement(By.xpath("//input[@name='q']"));
		searchBar.sendKeys("Samsung Galaxy S10");
		searchBar.submit();
		
		// Wait till web element 'Mobile' is available . once visible clicking on it
		WebElement mobileElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Mobiles']")));
		Assert.assertTrue(mobileElement.isDisplayed());
		mobileElement.click();
		
		// Wait till in filter - check box 'Samsung' and 'flipkartAssured' is visible . once visible clicking on it
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='SAMSUNG']/div/label/div[1]"))).click();
		WebElement flipAssuredImg =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section/label/div[2]/div/img[contains(@src,'fa_62673a.png')]")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", flipAssuredImg );
		
		// Wait till sort by is available. once visible clicking on "Price -- High to Low"
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Sort By')]/following-sibling::div[contains(text(),'Price -- High to Low')]"))).click();
		new WebDriverWait(driver, Duration.ofSeconds(30));

		
		// Finding the list of mobiles and looping through each mobile to print it.
		List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class,'_3Mn1Gg')][2]/div[contains(@class,'_1AtVbE')]"));
		int count = 0;
		for(WebElement element : elements) {

			boolean exists = element.findElements(By.xpath(".//div/div/div/a")).size() != 0;
			if(exists) {
				try {
					count++;
					WebElement linkElement = element.findElement(By.xpath(".//div/div/div/a"));
					WebElement nameElement = element.findElement(By.xpath(".//div/div/div/a/div[2]/div/div[1]"));
					WebElement priceElement = element.findElement(By.xpath(".//div[contains(@class,'col-5-12')]/div/div/div[1]"));
				
					System.out.println("-----------------------****************************-------------------------------");
					System.out.println("      Product Name :- "+ nameElement.getText());
					System.out.println("      Display Price :- "+priceElement.getText());
					System.out.println("      Link to Product Details Page :- "+linkElement.getAttribute("href"));
					System.out.println("-----------------------****************************-------------------------------");

				}catch(Exception e) {
					  System.out.println("Mobile not available");
				}	
			}

		}
		
		
		// Setting the status of test as 'passed' or 'failed' based on the basis of count of mobiles; 
	    if (count!=0) {
	      jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"List Of Mobile Found\"}}");
	    }
	    else {
	      jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"\"List Of Mobile NOT Found\"}}");
	    }
	    
	    driver.quit();
	
    }
}
