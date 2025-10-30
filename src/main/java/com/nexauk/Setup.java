package com.nexauk;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class Setup {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
    	
    	 try {
             // Launch browser
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
             driver.manage().window().maximize();
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

             System.out.println("✅ Chrome browser launched successfully!");

             // 2️⃣ Navigate to your website
             driver.get("https://www.next.co.uk/");
             System.out.println("🌐 Opened website: " + driver.getCurrentUrl());

             // 3️⃣ Accept cookies automatically
             Utilities.acceptCookiesIfPresent(driver);

         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("❌ Failed to launch Chrome: " + e.getMessage());
         }
     }
    	
    @AfterMethod
    public void tearDown() {
      //if(driver != null) {
            driver.quit();
            System.out.println("🚗 Browser closed successfully!");
        }
    }

