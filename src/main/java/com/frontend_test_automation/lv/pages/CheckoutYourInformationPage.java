package com.frontend_test_automation.lv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutYourInformationPage {
    private final WebDriver driver;
    private final By checkoutTitleS = By.className("title"); // selector for page title
    private final By continueButtonS = By.id("continue"); // selector for continue button
    private final By firstNameInputS = By.id("first-name"); // selector for first name input
    private final By lastNameInputS = By.id("last-name"); // selector for last name input
    private final By postalCodeInputS = By.id("postal-code"); // selector for postal code input

    public CheckoutYourInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getCheckoutTitle() {
        return this.driver.findElement(checkoutTitleS);
    }

    public boolean isDisplayed() {
        return getCheckoutTitle().isDisplayed();
    }

    public void fill_personal_info(String firstName,
                                   String lastName,
                                   String postalCode){
        driver.findElement(firstNameInputS).sendKeys(firstName);
        driver.findElement(lastNameInputS).sendKeys(lastName);
        driver.findElement(postalCodeInputS).sendKeys(postalCode);
    }

    public void click_on_continue_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueButtonS));
        continueButton.click();
    }
}