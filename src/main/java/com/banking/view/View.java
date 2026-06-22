package com.banking.view;

import com.banking.controller.AccountController;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.InsufficientFundsException;
import com.banking.exception.ValidationException;
import com.banking.model.Account;
import com.banking.model.AccountType;
import com.banking.util.Validator;

import java.util.Scanner;

public class View {

    private static View instance;
    private final AccountController controller;
    private final Scanner scanner = new Scanner(System.in);

    private View() {
        this.controller = AccountController.getInstance();
    }

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

    public void run() {

        boolean running = true;
        while (running) {
            System.out.println("\n========== Banking System ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Update Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Transfer Money");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String choiceInput = scanner.nextLine();
            switch (choiceInput.trim()) {
                case "1" -> createAccount();
                case "2" -> deleteAccount();
                case "3" -> updateAccount();
                case "4" -> deposit();
                case "5" -> withdraw();
                case "6" -> transferMoney();
                case "7" -> running = false;
                default -> System.out.println("Invalid choice. Please enter 1-7.");
            }
        }
        System.out.println("Thank you for using Banking System. Goodbye!");
        scanner.close();
    }

    private void createAccount() {
        System.out.println("\n--- Create Account ---");

        String name = null;
        while (name == null) {
            System.out.print("Enter Name: ");
            try {
                name = Validator.validateName(scanner.nextLine());
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        String phone = null;
        while (phone == null) {
            System.out.print("Enter Phone Number: ");
            try {
                phone = Validator.validatePhone(scanner.nextLine());
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        String email = null;
        while (email == null) {
            System.out.print("Enter Email: ");
            try {
                email = Validator.validateEmail(scanner.nextLine());
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        AccountType accountType = null;
        while (accountType == null) {
            System.out.print("Enter Account Type (SAVINGS/CURRENT): ");
            try {
                accountType = AccountType.valueOf(Validator.validateAccountType(scanner.nextLine()));
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        int initialAmount = -1;
        while (initialAmount == -1) {
            System.out.print("Enter Initial Deposit (500-5000): ");
            try {
                int amount = Validator.validateAmount(scanner.nextLine());
                initialAmount = Validator.validateInitialDeposit(amount);
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        Account account = controller.createAccount(name, phone, email, accountType, initialAmount);
        System.out.println("Account created successfully!" + account);
    }

    private void deleteAccount() {
        System.out.println("\n--- Delete Account ---");

        int accountNumber = readAccountNumber();

        try {
            String message = controller.deleteAccount(accountNumber);
            System.out.println(message);
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateAccount() {
        System.out.println("\n--- Update Account ---");

        int accountNumber = readAccountNumber();

        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email");
        System.out.println("4. Account Type");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine().trim();

        try {
            switch (choice) {
                case "1" -> {
                    String name = null;
                    while (name == null) {
                        System.out.print("Enter New Name: ");
                        try {
                            name = Validator.validateName(scanner.nextLine());
                        } catch (ValidationException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    System.out.println(controller.updateName(accountNumber, name));
                }
                case "2" -> {
                    String phone = null;
                    while (phone == null) {
                        System.out.print("Enter New Phone Number: ");
                        try {
                            phone = Validator.validatePhone(scanner.nextLine());
                        } catch (ValidationException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    System.out.println(controller.updatePhone(accountNumber, phone));
                }
                case "3" -> {
                    String email = null;
                    while (email == null) {
                        System.out.print("Enter New Email: ");
                        try {
                            email = Validator.validateEmail(scanner.nextLine());
                        } catch (ValidationException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    System.out.println(controller.updateEmail(accountNumber, email));
                }
                case "4" -> {
                    AccountType type = null;
                    while (type == null) {
                        System.out.print("Enter New Account Type (SAVINGS/CURRENT): ");
                        try {
                            type = AccountType.valueOf(Validator.validateAccountType(scanner.nextLine()));
                        } catch (ValidationException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    System.out.println(controller.updateAccountType(accountNumber, type));
                }
                default -> System.out.println("Invalid choice.");
            }
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deposit() {
        System.out.println("\n--- Deposit ---");

        int accountNumber = readAccountNumber();

        int amount = -1;
        while (amount == -1) {
            System.out.print("Enter Amount to Deposit: ");
            try {
                amount = Validator.validateAmount(scanner.nextLine());
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try {
            System.out.println(controller.depositMoney(accountNumber, amount));
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void withdraw() {
        System.out.println("\n--- Withdraw ---");

        int accountNumber = readAccountNumber();

        int amount = -1;
        while (amount == -1) {
            System.out.print("Enter Amount to Withdraw: ");
            try {
                amount = Validator.validateAmount(scanner.nextLine());
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try {
            System.out.println(controller.withdrawMoney(accountNumber, amount));
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void transferMoney() {
        System.out.println("\n--- Transfer Money ---");

        System.out.println("Sender:");
        int senderAccountNumber = readAccountNumber();

        System.out.println("Receiver:");
        int receiverAccountNumber = readAccountNumber();

        try {
            Validator.validateDifferentAccounts(
                    String.valueOf(senderAccountNumber),
                    String.valueOf(receiverAccountNumber)
            );
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        int amount = -1;
        while (amount == -1) {
            System.out.print("Enter Amount to Transfer: ");
            try {
                amount = Validator.validateAmount(scanner.nextLine());
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        try {
            System.out.println(controller.transferMoney(senderAccountNumber, receiverAccountNumber, amount));
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int readAccountNumber() {
        int accountNumber = -1;
        while (accountNumber == -1) {
            System.out.print("Enter Account Number: ");
            try {
                accountNumber = Validator.validateAccountNumber(scanner.nextLine());
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return accountNumber;
    }

}
