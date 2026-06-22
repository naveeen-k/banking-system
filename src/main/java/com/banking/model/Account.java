package com.banking.model;

public class Account {
    private int accountNumber;
    private String name;
    private String phoneNumber;
    private String email;
    private int initialDeposit;
    private int balance;
    private AccountType accountType;

    public Account(int accountNumber, String name, String phoneNumber, String email,
                   int initialDeposit, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.initialDeposit = initialDeposit;
        this.balance = initialDeposit;
        this.accountType = accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getInitialDeposit() {
        return initialDeposit;
    }

    public int getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "\n------ Account Details ------" +
                "\nAccount Number : " + accountNumber +
                "\nName           : " + name +
                "\nPhone          : " + phoneNumber +
                "\nEmail          : " + email +
                "\nAccount Type   : " + accountType +
                "\nBalance        : " + balance +
                "\n-----------------------------";
    }
}