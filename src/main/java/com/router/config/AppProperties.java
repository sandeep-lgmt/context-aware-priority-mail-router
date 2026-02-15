package com.router.config;

import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static final Properties props = new Properties();

    static {
        try (InputStream in =
                 AppProperties.class
                     .getClassLoader()
                     .getResourceAsStream("application.properties")) {

            props.load(in);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
