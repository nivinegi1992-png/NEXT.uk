package com.nexauk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class Base {

	public WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(@Optional("chrome") String browserName) {
		try {
			// Step 1️⃣: Initialize browser dynamically
			switch (browserName.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				System.out.println("🚀 Launching Chrome Browser");
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				System.out.println("🟦 Launching Microsoft Edge");
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				System.out.println("🦊 Launching Mozilla Firefox");
				break;

			default:
				throw new IllegalArgumentException("❌ Invalid browser name: " + browserName);
			}

			// Step 2️⃣: Common browser setup
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Step 3️⃣: Launch website
			driver.get("https://www.next.co.uk/");
			System.out.println("🌐 Navigated to: " + driver.getCurrentUrl());

			// Step 4️⃣: Handle cookies
			Utilities.acceptCookiesIfPresent(driver);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Error launching browser: " + e.getMessage());
		}
	}

	// 🧩 Tear down logic with screenshot-on-failure (from previous setup)
	@AfterMethod
	public void tearDown(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				System.out.println("❌ Test Failed: " + result.getName());
				Utilities.takeFullPageScreenshot(driver, result.getName());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				System.out.println("✅ Test Passed: " + result.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// if (driver != null) {
			driver.quit();
			System.out.println("🚗 Browser closed successfully!");
		}
	}
}
