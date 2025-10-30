package com.nextwebsite;

	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class ChromeLaunchCheck {
	    public static void main(String[] args) {
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.get("https://www.google.com");
	        System.out.println("🎉 Chrome launched and navigated to Google!");
	        driver.quit();
	    }
	}

