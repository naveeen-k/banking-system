package com.banking.repository;

import com.banking.model.Account;

public interface AccountRepository {
    void save(int accountNumber, Account account);
    Account findById(int accountNumber);
    void delete(int accountNumber);
}

