package com.ankit.framework.pages;

import com.ankit.framework.config.ConfigManager;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login");
    private final By loginError = By.cssSelector(".login-error");

    public LoginPage() {
        super();
    }

    public LoginPage open() {
        driver.get(ConfigManager.getBaseUrl() + "/practice-test-login/");
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public HomePage clickLogin() {
        click(loginButton);
        return new HomePage();
    }

    public boolean isLoginErrorDisplayed() {
        return isDisplayed(loginError);
    }
}
