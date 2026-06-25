package com.banking.repository;

import com.banking.model.Account;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountRepository implements AccountRepository {

    private static InMemoryAccountRepository instance;
    private final Map<Integer, Account> accounts = new HashMap<>();

    private InMemoryAccountRepository() {
    }

    public static InMemoryAccountRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryAccountRepository();
        }
        return instance;
    }

    @Override
    public void save(int accountNumber, Account account) {
        accounts.put(accountNumber, account);
    }

    @Override
    public Account findById(int accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public void delete(int accountNumber) {
        accounts.remove(accountNumber);
    }


}
