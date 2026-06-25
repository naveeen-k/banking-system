package com.banking.repository;

import com.banking.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTransactionRepository implements TransactionRepository {

    private static InMemoryTransactionRepository instance;
    private final Map<Integer, List<Transaction>> transactionHistory = new HashMap<>();

    private InMemoryTransactionRepository() {
    }

    public static InMemoryTransactionRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryTransactionRepository();
        }
        return instance;
    }

    @Override
    public void save(final Transaction transaction) {
        final int accountNumber = transaction.getAccountNumber();

        transactionHistory.computeIfAbsent(accountNumber, k -> new ArrayList<>()).add(transaction);
    }
}
