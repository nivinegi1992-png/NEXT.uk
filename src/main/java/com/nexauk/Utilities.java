package com.nexauk;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class Utilities {

    // 🔹 Scroll down gradually until end of page
    public static void scrollTillEnd(WebDriver driver) throws InterruptedException {JavascriptExecutor js = (JavascriptExecutor) driver;
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        while (true) {js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000); // Wait for content to load
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) break;
            lastHeight = newHeight;
        }
    }

    // 🔹 Capture Screenshot (with timestamp)
    public static String takeScreenshot(WebDriver driver, String fileName) throws Exception {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), Paths.get("./screenshots/" + fileName + "_" + timestamp + ".png"));
        System.out.println("📸 Screenshot saved: " + fileName + "_" + timestamp + ".png");
		return timestamp;
    }

    // 🔹 Wait for a few seconds (basic wait)
    public static void hardWait(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

	public static void waitForPageLoad(WebDriver driver, int i) {
		// TODO Auto-generated method stub
		
	}

	public static String takeFullPageScreenshot(WebDriver driver, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void acceptCookiesIfPresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement acceptButton = wait.until(ExpectedConditions.presenceOfElementLocated(
		        By.cssSelector("button#onetrust-accept-btn-handler, button[title='Accept All Cookies'], button[class*='accept']")));
		
		if (acceptButton.isDisplayed()) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptButton);
		    System.out.println("🍪 Cookies popup accepted successfully!");
		}
	}
}

