package com.banking.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(final String accountNumber) {
        super("Account not found: " + accountNumber);
    }
}
