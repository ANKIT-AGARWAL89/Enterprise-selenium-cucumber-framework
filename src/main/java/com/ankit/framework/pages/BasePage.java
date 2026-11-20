package com.ankit.framework.pages;

import com.ankit.framework.driver.DriverManager;
import com.ankit.framework.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtils waitUtils;
    protected final Logger logger;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.waitUtils = new WaitUtils();
        this.logger = LogManager.getLogger(getClass());
    }

    protected void click(By locator) {
        int attempts = 0;

        while (attempts < 2) {
            try {
                waitUtils.waitForClickable(locator);
                driver.findElement(locator).click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                logger.warn(
                        "Stale element while clicking {}. Retry {}",
                        locator,
                        attempts
                );
            }
        }

        throw new IllegalStateException(
                "Unable to click element after retry: " + locator
        );
    }

    protected void type(By locator, String text) {
        int attempts = 0;

        while (attempts < 2) {
            try {
                waitUtils.waitForVisibility(locator);
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                logger.warn(
                        "Stale element while typing into {}. Retry {}",
                        locator,
                        attempts
                );
            }
        }

        throw new IllegalStateException(
                "Unable to type into element after retry: " + locator
        );
    }

    protected String getText(By locator) {
        waitUtils.waitForVisibility(locator);
        return driver.findElement(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            waitUtils.waitForVisibility(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
