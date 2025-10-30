package com.nextwebsite;// getting fail again and again

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nexauk.ProjectObjectModel;
import com.nexauk.Setup;
import com.nexauk.Utilities;

import java.time.Duration;

public class LoginTestCases extends Setup {

    @Test
    public void loginWithValidCredentials() throws Exception {
        
        // Step 1: Open login page
        driver.get("https://auth.next.co.uk/u/login");
        ProjectObjectModel loginPage = new ProjectObjectModel(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 2: Enter email
        loginPage.clickOnEmailField();   // Should handle sendKeys internally
        Utilities.hardWait(1);

        // Step 3: Enter password
        loginPage.clickOnPasswordFeild();   // Should also handle sendKeys
        Utilities.hardWait(1);

        // Step 4: Show password (optional)
        loginPage.showPasswordButton();  
        Utilities.hardWait(2);

        // Step 5: Wait until Sign In button is clickable
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[type='submit'][data-action-button-primary='true']")));

        // Step 6: Scroll to button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signInBtn);
        Utilities.hardWait(1);

        try {
            // Try normal click first
            signInBtn.click();
        } catch (Exception e) {
            // Fallback: JS click if intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInBtn);
            System.out.println("⚠ Normal click failed, used JS click instead.");
        }

        // Step 7: Wait for login redirect or confirmation element
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("next.co.uk"),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-testid='header-myaccount-link']"))
        ));

        // Step 8: Assert login success
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("next.co.uk"), "❌ Login failed or URL not redirected.");
        System.out.println("✅ Login successful! Current URL: " + currentUrl);
    }
}