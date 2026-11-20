package com.ankit.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public final class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static <T> T read(String resourcePath, Class<T> clazz) {
        try (InputStream inputStream =
                     JsonUtils.class.getClassLoader()
                             .getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new IllegalArgumentException(
                        "JSON resource not found: " + resourcePath
                );
            }

            return OBJECT_MAPPER.readValue(inputStream, clazz);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to deserialize JSON: " + resourcePath, e
            );
        }
    }
}
