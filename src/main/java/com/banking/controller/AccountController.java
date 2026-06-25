package com.banking.controller;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InsufficientFundsException;
import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.service.BankingService;

public class AccountController {

    private static AccountController instance;
    private final BankingService service;

    private AccountController() {
        this.service = BankingService.getInstance();
    }

    public static AccountController getInstance() {
        if (instance == null) {
            instance = new AccountController();
        }
        return instance;
    }

    public Account createAccount(final String name, final String phone, final String email,
                                 final AccountType type, final int deposit) {
        return service.createAccount(name, phone, email, type, deposit);
    }

    public String deleteAccount(final int accountNumber) throws AccountNotFoundException {
        return service.deleteAccount(accountNumber);
    }

    public String updateName(final int accountNumber, final String name) throws AccountNotFoundException {
        return service.updateName(accountNumber, name);
    }

    public String updatePhone(final int accountNumber, final String phone) throws AccountNotFoundException {
        return service.updatePhone(accountNumber, phone);
    }

    public String updateEmail(final int accountNumber, final String email) throws AccountNotFoundException {
        return service.updateEmail(accountNumber, email);
    }

    public String updateAccountType(final int accountNumber, final AccountType type) throws AccountNotFoundException {
        return service.updateAccountType(accountNumber, type);
    }

    public String depositMoney(final int accountNumber, final int amount) throws AccountNotFoundException {
        return service.depositMoney(accountNumber, amount);
    }

    public String withdrawMoney(final int accountNumber, final int amount)
            throws AccountNotFoundException, InsufficientFundsException {
        return service.withdrawMoney(accountNumber, amount);
    }

    public String transferMoney(final int senderAccountNumber, final int receiverAccountNumber, final int amount)
            throws AccountNotFoundException, InsufficientFundsException {
        return service.transferMoney(senderAccountNumber, receiverAccountNumber, amount);
    }

}
