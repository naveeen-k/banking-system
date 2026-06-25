package com.banking.repository;

import com.banking.util.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public final class Database {

    private Database() {
    }

    public static void initialize() {
        final String serverUrl = AppConfig.get("db.server.url");

        final String user = AppConfig.get("db.username");

        final String password = AppConfig.get("db.password");

        final String dbName = AppConfig.get("db.name");

        try (Connection conn = DriverManager.getConnection(serverUrl, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            stmt.executeUpdate("USE " + dbName);

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS account (" +
                            "account_number INT PRIMARY KEY," +
                            "name           VARCHAR(100) NOT NULL," +
                            "phone_number   VARCHAR(15)  NOT NULL," +
                            "email          VARCHAR(100) NOT NULL," +
                            "balance        INT          NOT NULL DEFAULT 0," +
                            "account_type   VARCHAR(20)  NOT NULL)"
            );

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS transaction_history (" +
                            "transaction_id  VARCHAR(50) PRIMARY KEY," +
                            "account_number  INT         NOT NULL," +
                            "type            VARCHAR(20) NOT NULL," +
                            "amount          INT         NOT NULL," +
                            "balance_after   INT         NOT NULL," +
                            "created_at      DATETIME    NOT NULL," +
                            "FOREIGN KEY (account_number) REFERENCES account(account_number))"
            );

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection() {
        final String url = AppConfig.get("db.url");

        final String user = AppConfig.get("db.username");

        final String password = AppConfig.get("db.password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database: " + e.getMessage(), e);
        }
    }
}
