package com.nextwebsite;//code is not working
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nexauk.Setup;
import com.next.Pages.FavoritesPage;

public class Favoritebutton extends Setup {

    @Test
    public void verifyItemAddedToFavorites() throws InterruptedException {
        driver.get("https://www.next.co.uk/favourites"); // product page

        FavoritesPage fav = new FavoritesPage(driver);

        // Step 1: Add item to favorites
        fav.addToFavorites();

        // Step 2: Open favorites
        fav.openFavorites();

        Thread.sleep(0); // better to replace with explicit wait

        // Step 3: Verify item is present
        Assert.assertTrue(fav.isItemInFavorites("Dress"), "❌ Product not found in favorites!");
    }
}
