package com.test.dvla.vehicle;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.dvla.vehicle.DVLAOriginPage;
import com.test.dvla.vehicle.RegistrationInputPage;
import com.test.dvla.vehicle.ResultsPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/ServiceBean.xml"})

public class VehicleInformationValidation {
	
	@Autowired
	SourceFileServiceBean service;
	
	@Test
	public void VehicleInfoValidation() 
	{
		List<String> FinalVehInfo = VehicleInformationTest();
		FinalVehInfo.size();
		
		WebDriver driver;
		
		// Set the property for the Gecko Driver
		System.setProperty("webdriver.gecko.driver", "F:\\Selenium_Gecko\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		String baseURL = "https://www.gov.uk/get-vehicle-information-from-dvla";
		
		// Iterate through the list of Vehicle Registration Numbers
		for (int i = 0; i < FinalVehInfo.size(); i++) {

				String VehicleInfo = FinalVehInfo.get(i);
				
				String[] VehicleInfo_Array = VehicleInfo.split("_");
				String Registration_Number = VehicleInfo_Array[0]; 		// Extract Expected Registration Number
				String Expected_Vehicle_Make = VehicleInfo_Array[1]; 	// Extract Expected Vehicle_Make
				String Expected_Vehicle_Colour = VehicleInfo_Array[2];  // Extract Expected Vehicle_Colour
				
				driver.get(baseURL);
			
				// Page Object Model Implementation
				DVLAOriginPage.StartNowLink(driver).click();
				
				// Explicit Wait for the Registration Input Page to load successfully before we enter the Registration Number
				WebElement element = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("Vrm")));
				
				
				WebElement element_RegNum = RegistrationInputPage.RegistrationNumberTextBox(driver);
				element_RegNum.clear();
				
				// Enter the Registration_Number read from the Source Excel Files -- Page Object Model Implementation
				element_RegNum.sendKeys(Registration_Number);
				RegistrationInputPage.RegistrationNumberSubmit(driver).click();
				
				// Read the actual Vehicle_Make value from DVLA Web page -- Page Object Model Implementation
				WebElement VehicleMake = ResultsPage.VehicleMake(driver);
				String Actual_Vehicle_Make = VehicleMake.getText();

				// Read the actual Vehicle_Colour value from DVLA Web page -- Page Object Model Implementation
				WebElement VehicleColour = ResultsPage.VehicleColour(driver);
				String Actual_Vehicle_Colour = VehicleColour.getText();
	
				// Validate the Expected Vehicle Make and Colour with the Actual Values 
				Assert.assertEquals(Expected_Vehicle_Make, Actual_Vehicle_Make);
				Assert.assertEquals(Expected_Vehicle_Colour, Actual_Vehicle_Colour);
			}
			driver.close();

	}

	public List<String> VehicleInformationTest() 
	{		
		List<String> ListofValidFiles = service.vehicleInformation();
		
		List<String> VehInfoList = new ArrayList<String>(); 
		
		for (int i = 0; i < ListofValidFiles.size(); i++) {
			
			String excelFilePath = ListofValidFiles.get(i);
			
			try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	         
	        Workbook workbook = new XSSFWorkbook(inputStream);
		
			Sheet firstSheet = workbook.getSheetAt(0);
	        Iterator<Row> iterator = firstSheet.iterator();
	         
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	             
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                VehInfoList.add(cell.getStringCellValue());
	                System.out.print(cell.getStringCellValue()); 
	            }
	            System.out.println();
	        }
	        
	        workbook.close();
	        inputStream.close();			
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return VehInfoList;
	}

}
