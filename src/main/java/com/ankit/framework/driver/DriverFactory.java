package com.ankit.framework.driver;

import com.ankit.framework.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browser, String execution) {
        if ("local".equalsIgnoreCase(execution)) {
            return createLocalDriver(browser);
        }

        if ("grid".equalsIgnoreCase(execution)) {
            return createRemoteDriver(browser);
        }

        throw new IllegalArgumentException(
                "Unsupported execution mode: " + execution
        );
    }

    private static WebDriver createLocalDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(chromeOptions());
            case "firefox" -> new FirefoxDriver(firefoxOptions());
            case "edge" -> new EdgeDriver(edgeOptions());
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        };
    }

    private static WebDriver createRemoteDriver(String browser) {
        try {
            return switch (browser.toLowerCase()) {
                case "chrome" -> new RemoteWebDriver(
                        URI.create(ConfigManager.getGridUrl()).toURL(),
                        chromeOptions()
                );
                case "firefox" -> new RemoteWebDriver(
                        URI.create(ConfigManager.getGridUrl()).toURL(),
                        firefoxOptions()
                );
                case "edge" -> new RemoteWebDriver(
                        URI.create(ConfigManager.getGridUrl()).toURL(),
                        edgeOptions()
                );
                default -> throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
            };
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(
                    "Invalid Grid URL: " + ConfigManager.getGridUrl(), e
            );
        }
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();

        if (ConfigManager.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--window-size=1920,1080");
        return options;
    }

    private static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigManager.isHeadless()) {
            options.addArguments("-headless");
        }

        return options;
    }

    private static EdgeOptions edgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (ConfigManager.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--window-size=1920,1080");
        return options;
    }
}
