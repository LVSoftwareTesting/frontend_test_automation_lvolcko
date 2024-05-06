package com.frontend_test_automation.lv.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.frontend_test_automation.lv.pages.HomePage;
import com.frontend_test_automation.lv.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

public class AuthSteps {
    private final WebDriver driver = new ChromeDriver();
    private LoginPage loginPage;
    private HomePage homePage;

    @After()
    public void closeBrowser() {
        driver.quit();
    }

    @Given("I am on the login-page")
    public void i_am_on_the_login_page() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        assert(loginPage.isDisplayed());
    }

    @When("I enter valid {string} and {string}")
    public void i_enter_valid_username_and_password(String username, String password) {
        this.loginPage.login(username, password);
    }

    @When("I enter invalid {string} and {string}")
    public void i_enter_invalid_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @And("I click on the 'Login' button")
    public void i_click_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the home-page")
    public void i_should_be_redirected_to_the_home_page() {
        homePage = new HomePage(driver);
        assert(homePage.isDisplayed());
    }

    @When("I click on the 'Logout' button")
    public void i_click_on_the_logout_button() {
        homePage.click_on_menu_btn();
        homePage.click_on_logout_btn();
    }

    @Then("I should be redirected to the login-page")
    public void i_should_be_redirected_to_the_login_page() {
        assert(loginPage.isDisplayed());
    }

    @Then("I should see an error {string}")
    public void i_should_see_an_error_message(String message) {
        assert(loginPage.is_error_message_displayed());
        assert(Objects.equals(loginPage.get_error_message_text(), message));
    }
}

