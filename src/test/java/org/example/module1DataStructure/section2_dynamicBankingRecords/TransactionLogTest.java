package org.example.module1DataStructure.section2_dynamicBankingRecords;

import org.example.module1DataStructure.section2_dynamicBankingRecords.exercise2_1.TransactionLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionLogTest {

    TransactionLog transactionLog;

    @BeforeEach
    void setUp() {
        transactionLog = new TransactionLog();
    }

    @Test
    @DisplayName("Add transactions and verify count")
    void addTransaction() {
        transactionLog.addTransaction("DEPOSIT", 200.0, "24/03/2026");
        transactionLog.addTransaction("WITHDRAWAL", 100.0, "25/03/2026");
        transactionLog.addTransaction("WITHDRAWAL", 50.0, "26/03/2026");

        assertEquals(3, transactionLog.countTransactions());
    }

    @Test
    @DisplayName("Count returns 0 on empty log")
    void countTransactionsEmpty() {
        assertEquals(0, transactionLog.countTransactions());
    }

    @Test
    @DisplayName("Total deposited sums only DEPOSIT entries")
    void totalDeposited() {
        transactionLog.addTransaction("DEPOSIT", 500.0, "01/03/2026");
        transactionLog.addTransaction("WITHDRAWAL", 120.0, "02/03/2026");
        transactionLog.addTransaction("DEPOSIT", 2000.0, "03/03/2026");
        transactionLog.addTransaction("DEPOSIT", 1500.0, "04/03/2026");

        assertEquals(4000.0, transactionLog.totalDeposited(), 0.01);
    }

    @Test
    @DisplayName("Total deposited returns 0 when no deposits")
    void totalDepositedNoDeposits() {
        transactionLog.addTransaction("WITHDRAWAL", 100.0, "01/03/2026");
        transactionLog.addTransaction("WITHDRAWAL", 50.0, "02/03/2026");

        assertEquals(0.0, transactionLog.totalDeposited(), 0.01);
    }

    @Test
    @DisplayName("Print history does not throw on empty log")
    void printHistoryEmpty() {
        transactionLog.printHistory();
        assertEquals(0, transactionLog.countTransactions());
    }

    @Test
    @DisplayName("Print history runs after adding transactions")
    void printHistory() {
        transactionLog.addTransaction("DEPOSIT", 500.0, "01/03/2026");
        transactionLog.addTransaction("WITHDRAWAL", 120.0, "02/03/2026");

        transactionLog.printHistory();
        assertEquals(2, transactionLog.countTransactions());
    }
}
