package com.nextwebsite;// not working

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nexauk.Setup;
import com.nexauk.Utilities;

import java.io.File;

public class OrderTrackingFullPageScreenshotTest extends Setup {

	@Test
	public void captureOrderTrackingFullPage() {
		String url = "https://www.next.co.uk/secure/account/MyAccount/OrderTracking";
		driver.get(url);
		System.out.println("🌐 Navigated to Order-Tracking URL: " + url);

		// Wait for page fully loaded
		Utilities.waitForPageLoad(driver, 20);
		System.out.println("✅ Page load completed.");

		// Optional: if login is required before this page, include login steps here
		// e.g., do login, then navigate to this URL

		// Take full page screenshot
		String screenshotPath = Utilities.takeFullPageScreenshot(driver,"OrderTracking_FullPage");
		Assert.assertNotNull(screenshotPath, "❌ Screenshot path is null!");
		Assert.assertFalse(new File(screenshotPath).exists(), "❌ Screenshot file not found!");

		System.out.println("📸 Full page screenshot saved at: " + screenshotPath);
		System.out.println("🎯 Test passed: Screenshot captured successfully.");
	}

}
