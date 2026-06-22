package com.banking.controller;
//this is impotentq
// rebase practice in p1 branch
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

    public Account createAccount(String name, String phone, String email,
                                 AccountType type, int deposit) {
        return service.createAccount(name, phone, email, type, deposit);
    }

    public String deleteAccount(int accountNumber) throws AccountNotFoundException {
        return service.deleteAccount(accountNumber);
    }
    // this for push learnig
    // this from branch p1
    public String updateName(int accountNumber, String name) throws AccountNotFoundException {
        return service.updateName(accountNumber, name);
    }

    public String updatePhone(int accountNumber, String phone) throws AccountNotFoundException {
        return service.updatePhone(accountNumber, phone);
    }

    public String updateEmail(int accountNumber, String email) throws AccountNotFoundException {
        return service.updateEmail(accountNumber, email);
    }

    public String updateAccountType(int accountNumber, AccountType type) throws AccountNotFoundException {
        return service.updateAccountType(accountNumber, type);
    }
    // chnge the main brach for rebase practice
    public String depositMoney(int accountNumber, int amount) throws AccountNotFoundException {
        return service.depositMoney(accountNumber, amount);
    }

    public String withdrawMoney(int accountNumber, int amount)
            throws AccountNotFoundException, InsufficientFundsException {
        return service.withdrawMoney(accountNumber, amount);
    }

    public String transferMoney(int senderAccountNumber, int receiverAccountNumber, int amount)
            throws AccountNotFoundException, InsufficientFundsException {
        return service.transferMoney(senderAccountNumber, receiverAccountNumber, amount);
    }
// no idea i idea
}
