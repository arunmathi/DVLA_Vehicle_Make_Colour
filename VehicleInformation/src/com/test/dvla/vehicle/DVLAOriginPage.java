package com.test.dvla.vehicle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DVLAOriginPage {

	public static WebElement element = null;
	
	public static WebElement StartNowLink(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Start now"));
		return element;
	}
	
}
