package com.banking.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    public static void initialize() {

        String user = "root";
        String password = "2005";

        String serverUrl = "jdbc:mysql://localhost:3306/bankdb";

        try (Connection conn = DriverManager.getConnection(serverUrl, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS bankdb");
            stmt.executeUpdate("USE bankdb");

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

            System.out.println("MySQL database ready.");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database: " + e.getMessage(), e);
        }
    }
}
