package com.ankit.framework.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By heading = By.cssSelector("h1");

    public HomePage() {
        super();
    }

    public boolean isDisplayed() {
        return isDisplayed(heading);
    }

    public String getHeading() {
        return getText(heading);
    }
}
