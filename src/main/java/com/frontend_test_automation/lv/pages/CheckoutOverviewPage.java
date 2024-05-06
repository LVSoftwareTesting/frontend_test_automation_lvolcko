package com.frontend_test_automation.lv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final By checkoutTitleS = By.className("title"); // selector for page title
    private final By finishButtonS = By.id("finish"); // selector for finish button
    private final By summarySubtotalLabelS = By.className("summary_subtotal_label"); // selector for subtotal label
    private final By summaryTaxLabelS = By.className("summary_tax_label"); // selector for tax label
    private final By summaryTotalLabelS = By.className("summary_total_label"); // selector for total label

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getSummarySubtotalLabel() {
        return driver.findElement(summarySubtotalLabelS);
    }

    private WebElement getSummaryTaxLabel() {
        return driver.findElement(summaryTaxLabelS);
    }

    private WebElement getSummaryTotalLabel() {
        return driver.findElement(summaryTotalLabelS);
    }

    private WebElement getCheckoutTitle() {
        return this.driver.findElement(checkoutTitleS);
    }

    public boolean isDisplayed() {
        return getCheckoutTitle().isDisplayed();
    }

    public void click_on_finish_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(finishButtonS));
        finishButton.click();
    }

    public void verifyPrices(double expectedSumOfItems) {
        double actualSumOfItems = Double
                .parseDouble(getSummarySubtotalLabel()
                        .getText()
                        .replace("Item total: $", "")
                        .trim());
        double tax = Double
                .parseDouble(getSummaryTaxLabel()
                        .getText()
                        .replace("Tax: $", "")
                        .trim());
        double actualTotal = Double
                .parseDouble(getSummaryTotalLabel()
                        .getText()
                        .replace("Total: $", "")
                        .trim());

        double includedTax = actualSumOfItems + tax;

        assert(expectedSumOfItems == actualSumOfItems);
        assert(actualTotal == includedTax);
    }
}