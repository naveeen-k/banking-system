package com.banking.service;

import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InsufficientFundsException;
import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.model.Transaction;
import com.banking.model.TransactionType;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.util.RepositoryFactory;

import java.util.UUID;

public class BankingService {

    private static BankingService instance;
    private static int accountCounter = 1000;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private BankingService() {
        this.accountRepository = RepositoryFactory.getAccountRepository();
        this.transactionRepository = RepositoryFactory.getTransactionRepository();
    }

    public static BankingService getInstance() {
        if (instance == null) {
            instance = new BankingService();
        }
        return instance;
    }

    public Account createAccount(String name,String phone, String email,
                                 AccountType type, int initialAmount) {
        int accountNumber = ++accountCounter;
        Account account = new Account(accountNumber, name, phone, email, initialAmount, type);

        accountRepository.save(accountNumber, account);
        return account;
    }

    public String deleteAccount(int accountNumber) throws AccountNotFoundException {
        if (accountRepository.findById(accountNumber) == null) {
            throw new AccountNotFoundException(String.valueOf(accountNumber));
        }
        accountRepository.delete(accountNumber);
        return "Account deleted successfully.";
    }

    public String updateName(int accountNumber, String name) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountNumber);
        account.setName(name);
        accountRepository.save(account.getAccountNumber(), account);
        return "Name updated successfully.";
    }

    public String updatePhone(int accountNumber, String phone) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountNumber);
        account.setPhoneNumber(phone);
        accountRepository.save(account.getAccountNumber(), account);

        return "Phone number updated successfully.";
    }

    public String updateEmail(int accountNumber, String email) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountNumber);
        account.setEmail(email);
        accountRepository.save(account.getAccountNumber(), account);

        return "Email updated successfully.";
    }

    public String updateAccountType(int accountNumber, AccountType type) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountNumber);
        account.setAccountType(type);
        accountRepository.save(account.getAccountNumber(), account);
        return "Account type updated successfully.";
    }

    public String depositMoney(int accountNumber, int amount) throws AccountNotFoundException {
        Account account = getAccountOrThrow(accountNumber);
        account.deposit(amount);
        recordTransaction(accountNumber, TransactionType.DEPOSIT, amount, account.getBalance());
        accountRepository.save(account.getAccountNumber(), account);

        return "Deposit successful. New balance: " + account.getBalance();
    }

    public String withdrawMoney(int accountNumber, int amount)
            throws AccountNotFoundException, InsufficientFundsException {
        Account account = getAccountOrThrow(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient balance. Available: " + account.getBalance());

        }
        account.withdraw(amount);
        accountRepository.save(account.getAccountNumber(), account);

        recordTransaction(accountNumber, TransactionType.WITHDRAW, amount, account.getBalance());
        return "Withdrawal successful. New balance: " + account.getBalance();
    }

    public String transferMoney(int senderAccountNumber, int receiverAccountNumber, int amount)
            throws AccountNotFoundException, InsufficientFundsException {
        Account sender = getAccountOrThrow(senderAccountNumber);
        Account receiver = getAccountOrThrow(receiverAccountNumber);
        if (sender.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient balance. Available: " + sender.getBalance());

        }
        sender.withdraw(amount);
        receiver.deposit(amount);
        accountRepository.save(sender.getAccountNumber(), sender);

        recordTransaction(senderAccountNumber, TransactionType.TRANSFER, amount, sender.getBalance());
        return "Transfer successful. Your new balance: " + sender.getBalance();
    }

    private Account getAccountOrThrow(int accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(String.valueOf(accountNumber));

        }
        return account;
    }

    private void recordTransaction(int accountNumber, TransactionType type,
                                   int amount, int balanceAfter) {
        Transaction txn = new Transaction(
                UUID.randomUUID().toString(),
                accountNumber, type, amount, balanceAfter
        );
        transactionRepository.save(txn);
    }

}
