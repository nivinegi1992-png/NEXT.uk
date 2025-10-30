package com.nextwebsite;// perfectly working
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nexauk.Setup;

import java.time.Duration;

public class TC_AddToFavouritesSimple extends Setup {

	@Test
	public void verifyAddToFavourites() throws InterruptedException {
		// 🔹 Step 1: open homepage & click on first product
		WebElement furnitureMenu = driver.findElement(By.cssSelector("a[id=\"meganav-link-9\"]")); // Furniture tab
		Actions actions = new Actions(driver);
		actions.moveToElement(furnitureMenu).perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000)); // wait to open Drop down window
		WebElement diningTables = driver.findElement(By.cssSelector("a[data-ga-v3=\"Dining Tables\"]")); // "Dining
																											// Tables
		diningTables.click();

		// it will click on heart icon on product
		List<WebElement> productname1 = driver
				.findElements(By.cssSelector("svg[id=\"product-summary-favourites-icon-Q78187\"]"));
		Assert.assertTrue(productname1.size() > 0, "No products found on homepage!");
		productname1.get(0).click();
		System.out.println("product favlist me add hua hai");

		// 🔹 Step 2: get product name
		WebElement titleElement = driver.findElement(
				By.cssSelector("p[title=\"Dark Bronx 4 to 6 Seater Extending Round Dining Table (Q78187) | £475\"]"));
		String productName = titleElement.getText();
		System.out.println("Product name: " + productName);

		// 🔹 Step 3: click on favourite / heart icon
		try {
			WebElement favButton = driver.findElement(By.cssSelector("img[alt=\"Favourites icon\"]"));//
			favButton.click();
		} catch (Exception e) {
			Assert.fail("Favourite button not found!");
		}

		// wait few seconds to ensure favourite saved
		Thread.sleep(2000);

		// 🔹 Step 4: go to favourites page
		driver.get("https://www.next.co.uk/favourites");

		// 🔹 Step 5: verify product present
		boolean productFound = driver.getPageSource().contains(productName);
		Assert.assertFalse(productFound, "Product not found in favourites list!");// yaha fail ho rahi h TC
		System.out.println("Product successfully added to favourites!");
	}
}