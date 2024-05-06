package com.frontend_test_automation.lv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomePage {
    private final WebDriver driver;
    private final By pageTitleS = By.className("app_logo"); // selector for page title
    private final By menuBtnS = By.id("react-burger-menu-btn"); // selector for menu button
    private final By backpackAddToCartBtnS = By.id("add-to-cart-sauce-labs-backpack"); // selector for add backpack to cart button
    private final By logoutBtnS = By.id("logout_sidebar_link"); // selector for logout button
    private final By cartBadgeS = By.cssSelector(".shopping_cart_badge"); // selector shopping cart badge
    private By itemLabelS = By.cssSelector(".inventory_item_label>a"); // selector for all labels of items
    private By itemDescS = By.className("inventory_item_desc"); // selector for all descriptions of items
    private By itemPriceS = By.className("inventory_item_price"); // selector for all prices of items

    private By addToCartBtnS = By.xpath("//button[contains(@class,'btn btn_primary')]");

    List<String> itemLabels = new ArrayList<>(); // list for all labels of items
    List<String> itemDescs = new ArrayList<>(); // list for all descriptions of items
    List<String> itemPrices = new ArrayList<>(); // list for all prices of items
    List<WebElement> buttons; // list for all add to cart buttons

    public List<String> getItemLabels() {
        return itemLabels;
    }

    public List<String> getItemDescs() {
        return itemDescs;
    }

    public List<String> getItemPrices() {
        return itemPrices;
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    private WebElement getCartBadge() {
        return driver.findElement(cartBadgeS);
    }

    private List<WebElement> getAllAddToCartBtns() {
        buttons = driver.findElements(addToCartBtnS);
        return buttons;
    }

    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOf(driver.findElement(pageTitleS)));
        return pageTitle.isDisplayed();
    }

    public void click_on_menu_btn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(menuBtnS));
        menuButton.click();
    }

    public void click_on_all_add_to_cart_btns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(backpackAddToCartBtnS));
        assert(addToCart.isDisplayed());
        saveItemsData();
        for (WebElement button : getAllAddToCartBtns()) {
            try {
                button.click();
            } catch (Exception e) {
                System.out.println("Unable to click on button: " + e.getMessage());
            }
        }
    }

    private void saveItemsData() {
        List<WebElement> itemLabelsElements = driver.findElements(itemLabelS);
        for (WebElement label : itemLabelsElements) {
            itemLabels.add(label.getText().trim());
        }

        List<WebElement> itemDescElements = driver.findElements(itemDescS);
        for (WebElement desc : itemDescElements) {
            itemDescs.add(desc.getText().trim());
        }

        List<WebElement> itemPricesElements = driver.findElements(itemPriceS);
        for (WebElement price : itemPricesElements) {
            itemPrices.add(price.getText().trim());
        }
    }

    public void click_on_logout_btn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logoutBtnS));
        logoutButton.click();
    }

    public boolean check_cart_badge_value() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cartBadge = wait.until(ExpectedConditions.elementToBeClickable(getCartBadge()));
        return Objects.equals(cartBadge.getText(), String.valueOf(buttons.size()));
    }

    public boolean check_cart_is_empty() {
        boolean isNotPresent = isElementNotPresent(driver, By.cssSelector(".shopping_cart_badge"));
        return isNotPresent;
    }

    private static boolean isElementNotPresent(WebDriver driver, By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.isEmpty();
    }

    public void click_on_cart_button() {
        getCartBadge().click();
    }

    public double sumPrices() {
        double sum = 0.0;
        for (String price : itemPrices) {
            String numericPart = price.replace("$", "");
            double priceValue = Double.parseDouble(numericPart);
            sum += priceValue;
        }
        return sum;
    }
}