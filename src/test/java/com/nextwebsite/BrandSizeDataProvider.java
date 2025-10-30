package com.nextwebsite;//code is not working today
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nexauk.ProjectObjectModel;
import com.nexauk.Setup;

import java.time.Duration;

public class BrandSizeDataProvider extends Setup {

	// 🔹 DataProvider for Brand Names
	@DataProvider(name = "brandData")
	public Object[][] getBrandData() {
		return new Object[][] {{ "Yumi" },{ "Calvin Klein" } };
	}

	// 🔹 DataProvider for Sizes
	@DataProvider(name = "sizeData")
	public Object[][] getSizeData() {
		return new Object[][] {{ "8" }, { "10" } };
	}

	// 🔹 Brand Filter Test (Data Driven)
	@Test(priority = 1, dataProvider = "brandData")
	public void verifyBrandSelection(String brandName) {
		driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
		ProjectObjectModel brandPage = new ProjectObjectModel(driver);

		brandPage.openBrandFilter();
		brandPage.searchBrandName(brandName);
		brandPage.selectBrand(brandName);

		// Explicit wait instead of Thread.sleep()
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.cssSelector("span[data-testid='applied-filter-label']"), brandName));

		// Validation
		Assert.assertTrue(brandPage.isBrandApplied(brandName), "❌ Brand filter not applied properly for: " + brandName);
		System.out.println("✅ Brand filter applied successfully for: " + brandName);
	}

	// 🔹 Size Filter Test (Data Driven)
	@Test(priority = 2, dataProvider = "sizeData")
	public void verifySizeFilterSelection(String sizeValue) {
		driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
		ProjectObjectModel filterPage = new ProjectObjectModel(driver);

		filterPage.openSizeFilter();
		filterPage.selectSize(sizeValue);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.cssSelector("span[data-testid='applied-filter-label']"), sizeValue));

		Assert.assertTrue(filterPage.isSizeApplied(sizeValue),
				"❌ Size filter not applied properly for size: " + sizeValue);
		System.out.println("✅ Size filter applied successfully for size: " + sizeValue);
	}
}
