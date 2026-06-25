package com.banking.util;

import com.banking.exception.ValidationException;

public class Validator {

    public static String validateName(final String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty.");
        }

        if (name.length() < 2 || name.length() > 20) {
            throw new ValidationException("Name must be 2-20 characters.");
        }

        if (!name.matches("^[A-Za-z][A-Za-z .'-]*$")) {
            throw new ValidationException("Name contains invalid characters.");
        }

        return name.trim();
    }

    public static String validatePhone(final String phone) throws ValidationException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new ValidationException("Phone number cannot be empty.");
        }

        if (!phone.trim().matches("^[6-9][0-9]{9}$")) {
            throw new ValidationException("Phone must be 10 digits starting with 6-9.");
        }

        return phone.trim();
    }

    public static String validateEmail(final String email) throws ValidationException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty.");
        }

        if (!email.trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new ValidationException("Invalid email format.");
        }

        return email.trim();
    }

    public static String validateAccountType(final String accountType) throws ValidationException {
        if (accountType == null || accountType.trim().isEmpty()) {
            throw new ValidationException("Account type cannot be empty.");
        }

        final String upper = accountType.trim().toUpperCase();
        if (!upper.equals("SAVINGS") && !upper.equals("CURRENT")) {
            throw new ValidationException("Account type must be SAVINGS or CURRENT.");
        }

        return upper;
    }

    public static int validateInitialDeposit(final int amount) throws ValidationException {
        if (amount < 500) {
            throw new ValidationException("Initial deposit must be at least 500.");
        }

        if (amount > 5000) {
            throw new ValidationException("Initial deposit cannot exceed 5000.");
        }

        return amount;
    }

    public static int validateAccountNumber(String input) throws ValidationException {
        if (input == null || input.trim().isEmpty()) {
            throw new ValidationException("Account number cannot be empty.");
        }

        input = input.trim();

        if (!input.matches("^[0-9]+$")) {
            throw new ValidationException("Account number must contain only digits.");
        }

        if (input.charAt(0) == '0') {
            throw new ValidationException("Account number cannot start with 0.");
        }

        return Integer.parseInt(input);
    }

    public static int validateAmount(final String input) throws ValidationException {
        if (input == null || input.trim().isEmpty()) {
            throw new ValidationException("Amount cannot be empty.");
        }

        try {
            final int amount = Integer.parseInt(input.trim());
            if (amount <= 0) {
                throw new ValidationException("Amount must be greater than 0.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new ValidationException("Amount must be a valid number.");
        }
    }

    public static void validateDifferentAccounts(final String from, final String to) throws ValidationException {
        if (from.equals(to)) {
            throw new ValidationException("Sender and receiver accounts must be different.");
        }
    }
}