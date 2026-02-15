package com.router.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.router.config.DatabaseConfig;

public class DatabaseTest {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DatabaseConfig.getConnection()) {
            System.out.println("MySQL connected successfully ✅");
        }
    }
}
