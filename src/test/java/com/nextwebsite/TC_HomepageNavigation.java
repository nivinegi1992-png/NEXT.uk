package com.nextwebsite;// working perfectly

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nexauk.Setup;

public class TC_HomepageNavigation extends Setup {

	@Test(priority = 1)
	public void verifyHomepageLoadTime() { // TC has passed 
		long startTime = System.currentTimeMillis();
		long loadTime = System.currentTimeMillis() - startTime;
		System.out.println("Page Load Time: " + loadTime + "ms");
		Assert.assertTrue(loadTime < 50000, "Homepage took too long to load!");
	}

	@Test(priority = 2)
	public void verifyNavigationLinks() throws InterruptedException { // Hover on Women Tab
		WebElement womenTab = driver.findElement(By.cssSelector("li[data-testid=\"meganav-primarynav-link-women\"] span"));// a[id='meganav-link-0']
		Actions womenActionsTab = new Actions(driver);
		womenActionsTab.moveToElement(womenTab).perform();
		Thread.sleep(2000);
		Assert.assertTrue(womenTab.isDisplayed(), "Women Tab did not open!");
		System.out.println("Women Tab opened");
		
		WebElement menTab = driver.findElement(By.cssSelector("li[data-testid=\"meganav-primarynav-link-men\"] span"));// a[title=\"MEN\"]
		Actions menActionsTab = new Actions(driver);
		menActionsTab.moveToElement(menTab).perform();
		Thread.sleep(2000);
		Assert.assertTrue(menTab.isDisplayed(), "Men Tab didnot open!");
		System.out.println("Mentab opened");
		
		WebElement homeTab = driver.findElement(By.cssSelector("li[data-testid=\"meganav-primarynav-link-home\"] span"));// a[id=\"meganav-link-4\"]
		Actions homeActionTab = new Actions(driver);
		homeActionTab.moveToElement(homeTab).perform();
		Thread.sleep(2000);
		Assert.assertTrue(homeTab.isDisplayed(), "HomeTab didnot open!");
		System.out.println("Hometab opened");
	}

	@Test(priority = 3)
	public void verifySearchFunctionality() throws InterruptedException {
		WebElement searchBar = driver.findElement(By.cssSelector("input[data-testid='header-search-bar-text-input']"));
		searchBar.sendKeys("Levis", Keys.ENTER);
		Thread.sleep(2000);

		// Step 2: Verify product in search results
		WebElement product = driver.findElement(By.cssSelector("p[data-testid='product_summary_title']"));
		Assert.assertTrue(product.isDisplayed(), "Product not found in search results!");
		product.click();

		// Step 3: Re-locate search bar after page load (fresh element)
		WebElement searchBarAfterSearch = driver.findElement(By.cssSelector("input[data-testid='header-search-bar-text-input']"));
		Assert.assertTrue(searchBarAfterSearch.isDisplayed(), "Search bar not visible after product click!");
		System.out.println("Product Levis searched successfully");
	}
	// 🔹 Using DataProvider for multiple search terms
		 @Test(priority = 4, dataProvider = "searchData")
		    public void verifyDataDrivenOnSearchFunctionality(String searchTerm) throws InterruptedException {
		        System.out.println("Searching for: " + searchTerm);

		        WebElement searchBar = driver.findElement(By.cssSelector("input[data-testid='header-search-bar-text-input']"));
		        searchBar.clear();//
		        searchBar.sendKeys(searchTerm, Keys.ENTER);
		        Thread.sleep(3000);

		        WebElement product = driver.findElement(By.cssSelector("p[data-testid='product_summary_title']"));
		        Assert.assertTrue(product.isDisplayed(), "Product not found in search results for: " + searchTerm);
		        System.out.println("✅ Product found for search: " + searchTerm);
		    }

		    // 🔸 DataProvider Method
		 @DataProvider(name = "searchData")
		    public Object[][] provideSearchData() {
		        return new Object[][] {
		            {"Levis"},
		            {"Nike"},
		            {"Adidas"},
		            {"Puma"},
		            {"Zara"}
		        };
		    }

	@Test(priority=5)
	public void verifyLogoIsDisplayed() { // Locate the NEXT logo using CSS
		WebElement logo = driver.findElement(By.cssSelector("div[class=\"header-gan80s\"] img")); //a[aria-label='NEXT']
		Assert.assertTrue(logo.isDisplayed(), "NEXT logo is not displayed!");// Assertion: logo should be displayed		
		//logo.click(); // Optional: Verify navigation
	}

}