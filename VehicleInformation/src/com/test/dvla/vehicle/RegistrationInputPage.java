package com.test.dvla.vehicle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationInputPage {
	
	public static WebElement element = null;
	
	public static WebElement RegistrationNumberTextBox(WebDriver driver)
	{
		element = driver.findElement(By.id("Vrm"));
		return element;
	}
	
	public static WebElement RegistrationNumberSubmit(WebDriver driver)
	{
		element = driver.findElement(By.name("Continue"));
		return element;
	}

}
