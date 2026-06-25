package com.banking.model;

public class Account {

    private final int accountNumber;
    private String name;
    private String phoneNumber;
    private String email;
    private final int initialDeposit;
    private int balance;
    private AccountType accountType;

    public Account(final int accountNumber, final String name, final String phoneNumber, final String email,
                   final int initialDeposit, final AccountType accountType) {
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

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }

    public void deposit(final int amount) {
        this.balance += amount;
    }

    public void withdraw(final int amount) {
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