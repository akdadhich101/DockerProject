package com.qa.dockerConcept;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstClass {
	
	@BeforeTest
	public void start() throws IOException, InterruptedException {
		StartDocker sd = new StartDocker();
		sd.start();
	}

	@Test
	public void method2() throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(20000);
		URL url = new URL("http://0.0.0.0:4444/wd/hub");
		 System.setProperty("webdriver.http.factory", "jdk-http-client");
		ChromeOptions options = new ChromeOptions();

	
		RemoteWebDriver driver = new RemoteWebDriver(url, options);
			driver.get("http://www.facebook.com");
			System.out.println(driver.getTitle());
	}
	
	@AfterTest
	public void stop() throws IOException, InterruptedException {
		StartDocker sd = new StartDocker();
		sd.stopDocker();
	}

}
