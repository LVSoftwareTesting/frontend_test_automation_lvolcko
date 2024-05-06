package com.frontend_test_automation.lv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class CheckoutCompletePage {
    private final WebDriver driver;
    private final By checkoutTitleS = By.className("title"); // selector for page title
    private final By backHomeButtonS = By.id("back-to-products"); // selector for back home button
    private final By completeTextS = By.className("complete-text"); // selector for complete text label

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getCheckoutTitle() {
        return this.driver.findElement(checkoutTitleS);
    }

    public boolean isDisplayed() {
        return getCheckoutTitle().isDisplayed();
    }

    public void click_on_back_home_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement backHomeButton = wait.until(ExpectedConditions.elementToBeClickable(backHomeButtonS));
        backHomeButton.click();
    }

    public boolean textIsVisible() {
        String completeString = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        return Objects.equals(driver.findElement(completeTextS).getText(), completeString);
    }
}