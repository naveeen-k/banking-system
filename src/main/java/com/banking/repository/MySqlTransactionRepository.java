package com.banking.repository;

import com.banking.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MySqlTransactionRepository implements TransactionRepository {

    private static MySqlTransactionRepository instance;

    private MySqlTransactionRepository() {
    }

    public static MySqlTransactionRepository getInstance() {
        if (instance == null) {
            instance = new MySqlTransactionRepository();
        }
        return instance;
    }

    @Override
    public void save(Transaction transaction) {
        String sql =
                "INSERT INTO transaction_history " +
                        "(transaction_id, account_number, type, amount, balance_after, created_at) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transaction.getTransactionId());
            ps.setInt(2, transaction.getAccountNumber());
            ps.setString(3, transaction.getType().name());
            ps.setInt(4, transaction.getAmount());
            ps.setInt(5, transaction.getBalanceAfter());
            ps.setTimestamp(6, Timestamp.valueOf(transaction.getTimestamp()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save transaction: " + e.getMessage(), e);
        }

    }
}
