package com.banking.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final String transactionId;
    private final int accountNumber;
    private final TransactionType type;
    private final int amount;
    private final int balanceAfter;
    private final LocalDateTime timestamp;

    public Transaction(final String transactionId, final int accountNumber,
                       final TransactionType type, final int amount, final int balanceAfter) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public TransactionType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + " | " + type
                + " | Amount: " + amount
                + " | Balance: " + balanceAfter;
    }
}