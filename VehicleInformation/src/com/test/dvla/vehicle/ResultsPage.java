package com.test.dvla.vehicle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {

	public static WebElement element = null;	
	
	public static WebElement VehicleMake(WebDriver driver)
	{
		element = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[2]/span[2]/strong"));
		return element;
	}
	
	public static WebElement VehicleColour(WebDriver driver)
	{
		element = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[3]/span[2]/strong"));
		return element;
	}
	
}
