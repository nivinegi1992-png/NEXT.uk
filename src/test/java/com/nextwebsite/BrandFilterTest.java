//package com.nextwebsite;  // delete this class after sometime
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.nexauk.ProjectObjectModel;
//import com.nexauk.Setup;
//
//public class BrandFilterTest extends Setup{ 
//	@Test(priority=2)
//
//    public void verifyYumiBrandSelection() throws InterruptedException {
//        driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
//        ProjectObjectModel brandPage = new ProjectObjectModel(driver);
//
//       brandPage.openBrandFilter();
//       brandPage.searchBrandName("Yumi");
//       brandPage.selectYumiBrand();
//
//        Thread.sleep(3000); // better: use explicit wait
//        Assert.assertTrue(brandPage.isYumiApplied(), "Yumi brand filter not applied properly");
//    }
//	
//	@Test(priority=1)
//	public void verifySize10FilterSelection() throws InterruptedException {
//	    driver.get("https://www.next.co.uk/shop/gender-women-category-dresses");
//	   // BrandFilterPage filterPage = new BrandFilterPage(driver);
//	}
//}
//
