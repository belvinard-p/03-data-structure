package org.example.module1DataStructure.section3_bankingOperationsProcessing.exercise3_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class BankAccount {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccount.class);
    private double balance;
    private final Stack<Double> previousBalances;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.previousBalances = new Stack<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            LOGGER.warn("Deposit amount cannot be negative: {}", amount);
            return;
        }

        previousBalances.push(balance);

        balance += amount;

        LOGGER.info("balance added {}", getBalance());
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            LOGGER.warn("Withdrawal amount must be positive: {}", amount);
            return;
        }
        if (amount > balance) {
            LOGGER.warn("Insufficient funds. Balance: {}, requested: {}", balance, amount);
            return;
        }

        previousBalances.push(balance);
        balance -= amount;

        LOGGER.info("Withdrawal: {} | Balance: {}", amount, balance);
    }

    public void undoLastTransaction() {
        if (previousBalances.isEmpty()) {
            LOGGER.warn("No transaction history to undo");
            return;
        }

        balance = previousBalances.pop();
        LOGGER.info("Undo | Balance restored to: {}", balance);
    }

}