package com.next.Pages;//page object class

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FavoritesPage {

    public WebDriver driver;

    // Locators
    public By favoritesHeaderIcon = By.cssSelector("span[data-testid=\"header-fav-badge-active\"]"); // heart icon in top header ----a[aria-label='Favourites']
    public By favoriteIcon = By.cssSelector("svg[id=\"product-summary-favourites-icon-M47181\"]"); // heart button on product page  ---
    public By favoriteItems = By.cssSelector("div[data-testid='wishlist-item']");     //wishlist items

    // Constructor
    public FavoritesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Add item to favorites
    public void addToFavorites() {
        driver.findElement(favoriteIcon).click();
    }

    // Open favorites from header
    public void openFavorites() {
        driver.findElement(favoritesHeaderIcon).click();
    }

    // Verify item exists in favorites
    public boolean isItemInFavorites(String productName) {
        return driver.findElements(favoriteItems).stream().anyMatch(e -> e.getText().contains(productName));
    }
}
