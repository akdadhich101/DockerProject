package com.qa.dockerConcept;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StartDocker {

	@Test
	public void start() throws IOException, InterruptedException {
		File f = new File("output1.txt");
		if(f.exists()) {
			f.delete();
		}
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start DockerUp.bat");
		Boolean flag = false;
		Thread.sleep(5000);
		String file = "output1.txt";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 30);
		long stoptime = cal.getTimeInMillis();
		
		while(System.currentTimeMillis() < stoptime ) {
			if(flag) {
				break;
			}
			 FileReader fr = new FileReader(file);
			 
		
		BufferedReader reader = new BufferedReader(fr);
		String currentLine  = reader.readLine();
		while(currentLine != null && !flag) {
			if(currentLine.contains("Running 4/4")) {
				System.out.println("server started");
				flag = true;
				break;
				}
			currentLine = reader.readLine();
			}
		fr.close();
		reader.close();
		
		}
		Assert.assertTrue(flag);
		
	}
	
	@Test
	public void stopDocker() throws IOException, InterruptedException {
		File f = new File("output2.txt");
		if(f.exists()) {
			f.delete();
			
		}
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start DockerDown.bat");
		Boolean flag = false;
		Thread.sleep(5000);
		String file = "output2.txt";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 30);
		long stoptime = cal.getTimeInMillis();
		 FileReader fr = new FileReader(file);
		while(System.currentTimeMillis() < stoptime ) {
			if(flag) {
				break;
			}
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine  = reader.readLine();
		while(currentLine != null && !flag) {
			if(currentLine.contains("Running 4/4")) {
				System.out.println("server stopped");
				flag = true;
				break;
				}
			currentLine = reader.readLine();
			}
		fr.close();
		reader.close();
		}
		Assert.assertTrue(flag);
		
		
		
	}
}
