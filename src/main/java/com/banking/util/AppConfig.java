package com.banking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     AppConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("application.properties not found on the classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties: " + e.getMessage(), e);
        }
    }

    private AppConfig() {
    }

    public static String get(final String key) {
        return properties.getProperty(key);
    }

    public static boolean isInMemory() {
        return "inmemory".equalsIgnoreCase(get("db.type"));
    }
}
