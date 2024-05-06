package com.frontend_test_automation.lv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private final By loginPageTitleS = By.xpath("//div[text()='Swag Labs']");  // selector for page title
    private final By usernameInputS = By.id("user-name"); // selector for username input
    private final By passwordInputS = By.id("password"); // selector for password input
    private final By loginButtonS = By.id("login-button"); // selector for login button
    private final By errorMessageS = By.cssSelector("div.error-message-container.error"); // selector for error message

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getLoginPageTitle() {
        return this.driver.findElement(loginPageTitleS);
    }

    private WebElement getUsernameInput() {
        return this.driver.findElement(usernameInputS);
    }

    private WebElement getPasswordInput() {
        return this.driver.findElement(passwordInputS);
    }
    private WebElement getLoginButton() {
        return this.driver.findElement(loginButtonS);
    }
    private WebElement getErrorMessage() {
        return this.driver.findElement(errorMessageS);
    }

    public boolean isDisplayed() {
        return getLoginPageTitle().isDisplayed();
    }

    public void enterUsername(String username) {
        getUsernameInput().sendKeys(username);
    }

    public void enterPassword(String password) {
        getPasswordInput().sendKeys(password);
    }

    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonS));
        loginButton.click();
    }

    public String get_error_message_text() {
        return getErrorMessage().getText().trim();
    }

    public boolean is_error_message_displayed() {
        return getErrorMessage().isDisplayed();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
    }
}