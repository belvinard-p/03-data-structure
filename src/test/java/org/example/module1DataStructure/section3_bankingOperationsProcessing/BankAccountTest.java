package org.example.module1DataStructure.section3_bankingOperationsProcessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(1000.0);
    }

    @Test
    @DisplayName("Initial balance is correct")
    void initialBalance() {
        assertEquals(1000.0, account.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Deposit increases balance")
    void deposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Withdraw decreases balance")
    void withdraw() {
        account.withdraw(200.0);
        assertEquals(800.0, account.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Withdraw with insufficient funds is rejected")
    void withdrawInsufficientFunds() {
        account.withdraw(5000.0);
        assertEquals(1000.0, account.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Undo deposit restores previous balance")
    void undoDeposit() {
        account.deposit(500.0);
        account.undoLastTransaction();
        assertEquals(1000.0, account.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Undo withdraw restores previous balance")
    void undoWithdraw() {
        account.withdraw(200.0);
        account.undoLastTransaction();
        assertEquals(1000.0, account.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Multiple undoes restore in reverse order")
    void multipleUndos() {
        account.deposit(500.0);
        account.withdraw(200.0);
        account.undoLastTransaction();
        assertEquals(1500.0, account.getBalance(), 0.01);
        account.undoLastTransaction();
        assertEquals(1000.0, account.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Undo with no history does not throw")
    void undoEmptyHistory() {
        assertDoesNotThrow(() -> account.undoLastTransaction());
        assertEquals(1000.0, account.getBalance(), 0.01);
    }
}
