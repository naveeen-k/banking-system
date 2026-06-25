package com.banking.util;

import com.banking.repository.*;

public class RepositoryFactory {

    public static AccountRepository getAccountRepository() {
        if (AppConfig.isInMemory()) {
            return InMemoryAccountRepository.getInstance();
        }
        return MySqlAccountRepository.getInstance();
    }

    public static TransactionRepository getTransactionRepository() {
        if (AppConfig.isInMemory()) {
            return InMemoryTransactionRepository.getInstance();
        }
        return MySqlTransactionRepository.getInstance();
    }
}
