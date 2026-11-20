package com.ankit.framework.utils;

import com.ankit.framework.driver.DriverManager;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static void attachScreenshot(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        if (driver instanceof TakesScreenshot screenshotDriver) {
            byte[] screenshot =
                    screenshotDriver.getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    "Failure Screenshot"
            );
        }
    }
}
