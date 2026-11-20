package com.ankit.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        load("config/config.properties");

        String environment = System.getProperty("env", PROPERTIES.getProperty("environment", "qa"));

        load("config/" + environment + ".properties");
    }

    private ConfigManager() {
    }

    private static void load(String resourcePath) {
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new IllegalStateException("Configuration file not found: " + resourcePath);
            }

            PROPERTIES.putAll(loadProperties(inputStream));

        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration: " + resourcePath, e);
        }
    }

    private static Properties loadProperties(InputStream inputStream) throws IOException {

        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }

        String value = PROPERTIES.getProperty(key);

        if (value == null) {
            throw new IllegalArgumentException("Configuration key not found: " + key);
        }

        return value;
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static int getTimeout() {
        return Integer.parseInt(get("timeout"));
    }

    public static String getEnvironment() {
        return get("environment");
    }

    public static String getExecution() {
        return get("execution");
    }

    public static String getGridUrl() {
        return get("grid.url");
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static String getApiBaseUrl() {
        return get("api.base.url");
    }
}
