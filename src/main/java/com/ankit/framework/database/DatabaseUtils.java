package com.ankit.framework.database;

import com.ankit.framework.config.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseUtils {

    private DatabaseUtils() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                ConfigManager.get("db.url"),
                ConfigManager.get("db.username"),
                ConfigManager.get("db.password")
        );
    }
}
