package com.nexauk;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectObjectModel {
	public WebDriver driver;

	// Constructor
	public ProjectObjectModel(WebDriver driver) {
		this.driver = driver;
	}

	public void openBrandFilter() {
		driver.findElement(By.cssSelector("button[data-testid=\"plp-filter-label-button-brand\"]")).click();

	}

	public void searchBrandName(String brandName) {
		WebElement searchBox = driver
				.findElement(By.cssSelector("div[data-testid=\"plp-hf-category-search-bar-input\"] input"));

		searchBox.clear();
		searchBox.sendKeys(brandName);
	}

	public void selectYumiBrand() {
		WebElement yumi = driver
				.findElement(By.cssSelector("label[data-testid=\"plp-facet-chec]kbox-brand-brand:yumi\"]"));
		if (!yumi.isSelected()) {
			yumi.click();
		}
	}

	public boolean isYumiApplied() {
		List<WebElement> products = driver.findElements(By.cssSelector(
				"p[title=\"Yumi Blue Cord Shirt Dress With All Over Squirrel Embroidery (AA8593) | £65\"]"));
		// return products.stream().allMatch(e -> e.getText().contains("Yumi"));
		return products.size() > 0 && products.stream().allMatch(e -> e.getText().contains("Yumi"));
	}

// Size filter
	public void openSizeFilter() {
		driver.findElement(By.cssSelector("button[data-testid=\"plp-filter-label-button-size\"]")).click();
	}

	public void selectSize10() {
		WebElement size10 = driver
				.findElement(By.cssSelector("label[data-testid=\"plp-facet-checkbox-size-size:10\"]"));
		if (!size10.isSelected()) {
			size10.click();
		}
	}

	public boolean isSize10Applied() {
		List<WebElement> products = driver.findElements(By.cssSelector("input[tabindex=\"-1\"]")); // you can enhance
																									// this to check
																									// size
		return products.size() > 0;
	}

	public void closeSizeFilter() {
		driver.findElement(By.cssSelector("button[data-testid=\"plp-filter-label-button-size\"]")).click();
	}

	public boolean isBrandApplied(String brandName) {
		return driver.getPageSource().contains(brandName);
	}

	public boolean isSizeApplied(String sizeValue) {
		return driver.getPageSource().contains(sizeValue);
	}

	// 🔹 Method to scroll down gradually
	public void scrollTillEnd() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
		while (true) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(2000); // Wait for content to load
			long newHeight = (long) js.executeScript("return document.body.scrollHeight");
			if (newHeight == lastHeight)
				break;
			lastHeight = newHeight;
		}
	}

	// 🔹 Method to take full page screenshot
	public void takeFullPageScreenshot(String fileName) throws Exception {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot.toPath(), Paths.get("./screenshots/" + fileName + ".png"));
		System.out.println("📸 Screenshot saved: " + fileName + ".png");
	}

	public void clickOnSignInButton() {
		WebElement signInButton = driver
				.findElement(By.cssSelector("div[class=\"cdebb54bf\"] button"));
		signInButton.click();
		System.out.println("Clicked on sign In Button button");
	}

	public void clickOnEmailField() {
		// Email input field
		WebElement emailField = driver.findElement(By.cssSelector("input[id='username']"));
		emailField.sendKeys("sayalihtpatil95@gmail.com");
		System.out.println("Entered in Email Feild");
	}
	public void clickOnPasswordFeild() {
		// Password input field
	    WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
	    passwordField.sendKeys("SayaliMatle1");
	    System.out.println("Password Entered successfully");
	}
	public void showPasswordButton() {
		// password show button
	    driver.findElement(By.cssSelector("a[class=\"showPassword\"]")).click();
	    System.out.println("clicked successfully on show password button");
	}
	
	
    // Brand Filter
	By brandFilterButton = By.cssSelector("button[data-testid='plp-filter-label-button-brand']");
	By brandSearchBox = By.cssSelector("div[data-testid='plp-hf-category-search-bar-input'] input");

	// Size Filter
	By sizeFilterButton = By.cssSelector("button[data-testid='plp-filter-label-button-size']");

	// Dynamic checkboxes
	public void selectBrand(String brandName) {
		driver.findElement(
				By.cssSelector("label[data-testid='plp-facet-checkbox-brand-brand:" + brandName.toLowerCase() + "']"))
				.click();
	}

	public void selectSize(String sizeValue) {
		driver.findElement(By.cssSelector("label[data-testid='plp-facet-checkbox-size-size:" + sizeValue + "']"))
				.click();
	}

}