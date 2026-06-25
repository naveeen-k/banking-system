package com.banking.repository;

import com.banking.model.Account;
import com.banking.model.AccountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlAccountRepository implements AccountRepository {

    private static MySqlAccountRepository instance;

    private MySqlAccountRepository() {
    }

    public static MySqlAccountRepository getInstance() {
        if (instance == null) {
            instance = new MySqlAccountRepository();
        }
        return instance;
    }

    @Override
    public void save(final int accountNumber, final Account account) {
        final String sql =
                "INSERT INTO account (account_number, name, phone_number, email, balance, account_type) " +
                        "VALUES (?, ?, ?, ?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "name = ?, phone_number = ?, email = ?, balance = ?, account_type = ?";

        try (final Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountNumber);
            ps.setString(2, account.getName());
            ps.setString(3, account.getPhoneNumber());
            ps.setString(4, account.getEmail());
            ps.setInt(5, account.getBalance());
            ps.setString(6, account.getAccountType().name());

            ps.setString(7, account.getName());
            ps.setString(8, account.getPhoneNumber());
            ps.setString(9, account.getEmail());
            ps.setInt(10, account.getBalance());
            ps.setString(11, account.getAccountType().name());

                ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save account: " + e.getMessage(), e);
        }
    }

    @Override
    public Account findById(final int accountNumber) {
        final String sql = "SELECT * FROM account WHERE account_number = ?";

        try (final Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToAccount(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find account: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(final int accountNumber) {
        final String deleteTransactions = "DELETE FROM transaction_history WHERE account_number = ?";
        final String deleteAccount = "DELETE FROM account WHERE account_number = ?";

        try (final Connection conn = Database.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(deleteTransactions);
                 PreparedStatement ps2 = conn.prepareStatement(deleteAccount)) {

                ps1.setInt(1, accountNumber);
                ps1.executeUpdate();
                ps2.setInt(1, accountNumber);
                ps2.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete account: " + e.getMessage(), e);
        }
    }

    private Account mapRowToAccount(final ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt("account_number"),

                rs.getString("name"),
                rs.getString("phone_number"),
                rs.getString("email"),
                rs.getInt("balance"),
                AccountType.valueOf(rs.getString("account_type"))
        );
    }


}


