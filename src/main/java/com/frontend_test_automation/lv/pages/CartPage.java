package com.frontend_test_automation.lv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final By cartTitleS = By.className("title"); // selector for page title
    private final By itemLableS = By.className("inventory_item_name"); // selector for item label
    private final By itemDescS = By.className("inventory_item_desc"); // selector for item description
    private final By itemPriceS = By.className("inventory_item_price"); // selector for item price
    private final By checkoutButtonS = By.id("checkout"); // selector for checkout button

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getCartTitle() {
        return this.driver.findElement(cartTitleS);
    }

    private WebElement getCheckoutButton() {
        return this.driver.findElement(checkoutButtonS);
    }

    public boolean isDisplayed() {
        return getCartTitle().isDisplayed();
    }

    public void i_can_see_all_products_with_details(
            List<String> itemLabels,
            List<String> itemDescs,
            List<String> itemPrices) {
        List<WebElement> itemLabelsElements = driver.findElements(itemLableS);
        for (WebElement label : itemLabelsElements) {
            assert(itemLabels.contains(label.getText().trim()));
        }

        List<WebElement> itemDescElements = driver.findElements(itemDescS);
        for (WebElement desc : itemDescElements) {
            assert(itemDescs.contains(desc.getText().trim()));
        }

        List<WebElement> itemPricesElements = driver.findElements(itemPriceS);
        for (WebElement price : itemPricesElements) {
            assert(itemPrices.contains(price.getText().trim()));
        }
    }

    public void click_on_checkout_button() {
        getCheckoutButton().click();
    }
}