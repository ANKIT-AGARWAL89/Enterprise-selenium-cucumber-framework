package com.ankit.framework.utils;

import java.util.UUID;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static String randomEmail() {
        return "user-" + UUID.randomUUID() + "@test.com";
    }

    public static String randomUsername() {
        return "user_" + UUID.randomUUID().toString().replace("-", "");
    }
}
