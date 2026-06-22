package com.banking.util;

import com.banking.repository.*;

import java.util.Objects;

public class RepositoryFactory {
    static String dataBase = "mysql";

    public static AccountRepository getAccountRepository() {
        if (Objects.equals(dataBase, "mysql")) {
            return MySqlAccountRepository.getInstance();
        }
        return InMemoryAccountRepository.getInstance();
    }

    public static TransactionRepository getTransactionRepository() {
        if (Objects.equals(dataBase, "mysql")) {
            return MySqlTransactionRepository.getInstance();
        }
        return InMemoryTransactionRepository.getInstance();
    }
}
