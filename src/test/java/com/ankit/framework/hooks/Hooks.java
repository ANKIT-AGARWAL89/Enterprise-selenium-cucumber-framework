package com.ankit.framework.hooks;

import com.ankit.framework.config.ConfigManager;
import com.ankit.framework.driver.DriverFactory;
import com.ankit.framework.driver.DriverManager;
import com.ankit.framework.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static final Logger logger =
            LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        logger.info(
                "Starting browser. Environment={}, Browser={}, Execution={}",
                ConfigManager.getEnvironment(),
                ConfigManager.getBrowser(),
                ConfigManager.getExecution()
        );

        WebDriver driver = DriverFactory.createDriver(
                ConfigManager.getBrowser(),
                ConfigManager.getExecution()
        );

        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                ScreenshotUtils.attachScreenshot(scenario);
            }
        } finally {
            DriverManager.quitDriver();
            logger.info(
                    "Scenario '{}' finished with status: {}",
                    scenario.getName(),
                    scenario.getStatus()
            );
        }
    }
}
