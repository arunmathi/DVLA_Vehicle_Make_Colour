package com.test.dvla.vehicle;


import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.File;
import java.util.*;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/ServiceBeans.xml"})

@Component
public class SourceFileServiceBean {
	
	public List<String> vehicleInformation() {
		
		List<String> results = new ArrayList<String>();
		
		String Source_Dir = "F:\\Workspace\\FirstSpringProject\\SourceFiles";
		
		File folder = new File(Source_Dir);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  
		    	  String fileExtension = listOfFiles[i].getName().substring(listOfFiles[i].getName().lastIndexOf(".")+1);

		    	  if(fileExtension.equalsIgnoreCase("xlsx") || fileExtension.equalsIgnoreCase("csv"))
		    	  {
		    		  results.add(listOfFiles[i].getAbsolutePath());
		    	  }
		      }
		    }
		   return results;
}

}
