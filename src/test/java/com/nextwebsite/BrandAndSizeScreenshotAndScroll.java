//package com.nextwebsite;// not working properly
//
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.nexauk.BrandFilterPage;
//import com.nexauk.Setup;
//
//import java.time.Duration;
//
//public class BrandAndSizeScreenshotAndScroll extends Setup {
//
//	@DataProvider(name = "brandData")
//	public Object[][] getBrandData() {
//		return new Object[][] { { "Yumi" }, { "Lipsy" } };
//	}
//
//	@DataProvider(name = "sizeData")
//	public Object[][] getSizeData() {
//		return new Object[][] { { "8" }, { "10" } };
//	}
//
//	
//	// 🔹 Brand Filter Test
//	@Test(priority = 1, dataProvider = "brandData")
//	public void verifyBrandSelection(String brandName) throws Exception {
//		driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
//		BrandFilterPage brandPage = new BrandFilterPage(driver);
//
//		brandPage.openBrandFilter();
//		brandPage.searchBrandName(brandName);
//		brandPage.selectBrand(brandName);
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//				"//span[contains(@data-testid,'applied-filter-label') and contains(text(),'" + brandName + "')]")));
//
//		// Wait until at least 1 product is visible
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='product-summary']")));
//
//		// Scroll through all items
//		scrollTillEnd();
//
//		// Take full page screenshot
//		takeFullPageScreenshot("Brand_" + brandName.replace(" ", "_"));
//
//		List<WebElement> products = driver.findElements(By.cssSelector("div[data-testid='product-summary']"));
//		int productCount = products.size();
//		Assert.assertTrue(productCount > 0, "❌ No products displayed for brand: " + brandName);
//		System.out.println("✅ Brand filter applied for " + brandName + " | Products: " + productCount);
//	}
//
//	// 🔹 Size Filter Test
//	@Test(priority = 2, dataProvider = "sizeData")
//	public void verifySizeFilterSelection(String sizeValue) throws Exception {
//		driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
//		BrandFilterPage filterPage = new BrandFilterPage(driver);
//
//		filterPage.openSizeFilter();
//		filterPage.selectSize(sizeValue);
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//				"//span[contains(@data-testid,'applied-filter-label') and contains(text(),'" + sizeValue + "')]")));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='product-summary']")));
//		scrollTillEnd();
//		takeFullPageScreenshot("Size_" + sizeValue);
//
//		List<WebElement> products = driver.findElements(By.cssSelector("div[data-testid='product-summary']"));
//		int productCount = products.size();
//		Assert.assertTrue(productCount > 0, "❌ No products displayed for size: " + sizeValue);
//		System.out.println("✅ Size filter applied for " + sizeValue + " | Products: " + productCount);
//	}
//}


package com.nextwebsite;//on hold as of now dont delete

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nexauk.ProjectObjectModel;
import com.nexauk.Setup;
import com.nexauk.Utilities;

import java.time.Duration;

public class BrandAndSizeScreenshotAndScroll extends Setup {

    // 🔹 Brand DataProvider
    @DataProvider(name = "brandData")
    public Object[][] getBrandData() {
        return new Object[][] { { "Yumi" }, { "Lipsy" } };
    }

    // 🔹 Size DataProvider
    @DataProvider(name = "sizeData")
    public Object[][] getSizeData() {
        return new Object[][] { { "8" }, { "4" } };
    }

    // 🔹 Brand Filter Test
    @Test(priority = 1, dataProvider = "brandData")
    public void verifyBrandSelection(String brandName) throws Exception {

        driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
        ProjectObjectModel brandPage = new ProjectObjectModel(driver);

        // Open brand filter
        brandPage.openBrandFilter();
        Utilities.hardWait(2);

        // Search and select brand
        brandPage.searchBrandName(brandName);
        Utilities.hardWait(1);
        brandPage.selectBrand(brandName);

        // ✅ Wait until at least one product appears
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='product-summary']")));

        // ✅ Scroll entire page and take screenshot
        Utilities.scrollTillEnd(driver);
        Utilities.takeScreenshot(driver, "Brand_" + brandName.replace(" ", "_"));

        // ✅ Verify product count
        List<WebElement> products = driver.findElements(By.cssSelector("div[data-testid='product-summary']"));
        Assert.assertTrue(products.size() > 0, "❌ No products displayed for brand: " + brandName);
        System.out.println("✅ Brand filter applied for " + brandName + " | Total products: " + products.size());
    }

    // 🔹 Size Filter Test
    @Test(priority = 2, dataProvider = "sizeData")
    public void verifySizeFilterSelection(String sizeValue) throws Exception {

        driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
        ProjectObjectModel filterPage = new ProjectObjectModel(driver);

        // Open size filter
        filterPage.openSizeFilter();
        Utilities.hardWait(2);

        // Select size (re-locate to prevent stale)
        try {
            filterPage.selectSize(sizeValue);
        } catch (StaleElementReferenceException e) {
            // Retry once if stale
            Utilities.hardWait(2);
            filterPage.selectSize(sizeValue);
        }

        // ✅ Wait for products to load
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='product-summary']")));

        // ✅ Scroll and take screenshot
        Utilities.scrollTillEnd(driver);
        Utilities.takeScreenshot(driver, "Size_" + sizeValue);

        // ✅ Verify product count
        List<WebElement> products = driver.findElements(By.cssSelector("div[data-testid='product-summary']"));
        Assert.assertTrue(products.size() > 0, "❌ No products displayed for size: " + sizeValue);
        System.out.println("✅ Size filter applied for " + sizeValue + " | Total products: " + products.size());
    }
}


