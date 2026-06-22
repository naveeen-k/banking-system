package com.banking.repository;

import com.banking.model.Transaction;

public interface TransactionRepository {
    void save(Transaction transaction);
}
