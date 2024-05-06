 package com.frontend_test_automation.lv.steps;

 import com.frontend_test_automation.lv.pages.*;
 import io.cucumber.java.After;
 import io.cucumber.java.en.*;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.chrome.ChromeDriver;

 public class ProductManagementSteps {
     private final WebDriver driver = new ChromeDriver();
     private LoginPage loginPage;
     private HomePage homePage;
     private CartPage cartPage;
     private CheckoutYourInformationPage checkoutYourInformationPage;
     private CheckoutOverviewPage checkoutOverviewPage;
     private CheckoutCompletePage checkoutCompletePage;

     @After()
     public void closeBrowser() {
         driver.quit();
     }

     @Given("I am logged in with valid {string} and {string}")
     public void i_am_logged_in_with_valid_username_and_password(String username, String password) {
         driver.manage().window().maximize();
         driver.get("https://www.saucedemo.com");
         loginPage = new LoginPage(driver);
         assert(loginPage.isDisplayed());
         loginPage.enterUsername(username);
         loginPage.enterPassword(password);
         loginPage.clickLogin();
         homePage = new HomePage(driver);
         assert(homePage.isDisplayed());
     }

      @And("I add all products to the cart")
      public void i_add_all_products_to_the_cart() {
          homePage.click_on_all_add_to_cart_btns();
      }

     @Then("I can see in cart badge same value as quantity of products listed on home-page")
     public void i_can_see_in_cart_badge_same_value_as_quantity_of_products_listed_on_home_page() {
          assert(homePage.check_cart_badge_value());
     }

     @And("I click on cart button")
     public void i_click_on_cart_button() {
         homePage.click_on_cart_button();
     }

     @Then("I am redirected to cart-page")
     public void i_am_redirected_to_cart_page() {
         cartPage = new CartPage(driver);
         assert(cartPage.isDisplayed());
     }

     @And("I can see all products from home-page listed in the cart")
     public void i_can_see_all_products_from_home_page_listed_in_the_cart() {
         cartPage.i_can_see_all_products_with_details(
                 homePage.getItemLabels(),
                 homePage.getItemDescs(),
                 homePage.getItemPrices()
         );
     }

     @And("I proceed to checkout and fill personal information: {string}, {string}, {string}")
     public void i_proceed_to_checkout_and_fill_personal_information(
             String firstName,
             String lastName,
             String postalCode) {
         cartPage.click_on_checkout_button();
         checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
         assert(checkoutYourInformationPage.isDisplayed());
         checkoutYourInformationPage.fill_personal_info(firstName, lastName, postalCode);
     }

     @And("I click on continue button")
     public void i_click_on_continue_button() {
         checkoutYourInformationPage.click_on_continue_button();
     }

     @Then("I am redirected to checkout overview page")
     public void iAmRedirectedToCheckoutOverviewPage() {
         checkoutOverviewPage = new CheckoutOverviewPage(driver);
         assert(checkoutOverviewPage.isDisplayed());
     }

     @And("I verify the prices")
     public void i_verify_the_prices_and_complete_the_purchase() {
         double expectedSumOfItems = homePage.sumPrices();
         checkoutOverviewPage.verifyPrices(expectedSumOfItems);
     }


     @And("I click on finish button")
     public void iClickOnFinishButton() {
         checkoutOverviewPage.click_on_finish_button();
     }

     @Then("I am redirected to checkout complete page")
     public void i_am_redirected_to_checkout_complete_page() {
         checkoutCompletePage = new CheckoutCompletePage(driver);
         assert(checkoutCompletePage.isDisplayed());
         assert(checkoutCompletePage.textIsVisible());
     }

     @And("I click on back to home page")
     public void i_click_on_back_to_home_page() {
         checkoutCompletePage.click_on_back_home_button();
     }

     @Then("cart is empty")
     public void cart_is_empty() {
         homePage.check_cart_is_empty();
     }
 }