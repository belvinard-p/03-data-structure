package org.example.module1DataStructure.section1_coreBankingStorage;

import org.example.module1DataStructure.section1_coreBankingStorage.exercise1_1.CreditUnionLedger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditUnionLedgerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditUnionLedgerTest.class);

    private static final String[] NAMES = {
            "Jean Pierre Ngono", "Brice Kamdem", "Serge Alain Ndzié",
            "Marie Claire Ndzi", "Paul Biya", "François Sengat Kuo",
            "Esther Ngo Mback", "Alain Belinga", "Christine Ayissi", "Joseph Tchouta"
    };
    private static final double[] BALANCES = {
            1_200_000.0, 125_000.00, 2_140_000.00, 170_000.00, 5_000_000.00,
            750_000.00, 300_000.00, 890_000.00, 450_000.00, 1_500_000.00
    };

    private CreditUnionLedger ledger;

    @BeforeEach
    void setup() {
        ledger = new CreditUnionLedger();
    }

    private void registerAllAccounts() {
        for (int i = 0; i < NAMES.length; i++) {
            ledger.register(i, NAMES[i], BALANCES[i]);
        }
    }

    private void registerFourAccounts() {
        for (int i = 0; i < 4; i++) {
            ledger.register(i, NAMES[i], BALANCES[i]);
        }
    }

    @Nested
    @DisplayName("Register Tests")
    class RegisterTest {

        @Test
        @DisplayName("should store name and balance at given index")
        void registerStoresData() {
            ledger.register(0, "Jean Pierre Ngono", 1_200_000.0);

            assertEquals("Jean Pierre Ngono", ledger.getAccountHolder(0));
            assertEquals(1_200_000.0, ledger.getBalance(0));
        }

        @Test
        @DisplayName("should store multiple accounts at different indices")
        void registerMultipleAccounts() {
            ledger.register(0, "Brice Kamdem", 125_000.00);
            ledger.register(4, "Serge Alain Ndzié", 214_000.00);
            ledger.register(9, "Marie Claire Ndzi", 170_000.00);

            assertEquals("Brice Kamdem", ledger.getAccountHolder(0));
            assertEquals("Serge Alain Ndzié", ledger.getAccountHolder(4));
            assertEquals("Marie Claire Ndzi", ledger.getAccountHolder(9));
        }

        @Test
        @DisplayName("should register 10 accounts with Cameroonian names")
        void registerTenAccounts() {
            registerAllAccounts();

            for (int i = 0; i < NAMES.length; i++) {
                assertEquals(NAMES[i], ledger.getAccountHolder(i));
                assertEquals(BALANCES[i], ledger.getBalance(i), 0.01);
            }

            assertEquals("Paul Biya", ledger.highestBalanceName());
            assertEquals(5_000_000.00, ledger.highestBalanceAmount());
        }

        @Test
        @DisplayName("should not crash on invalid index")
        void registerInvalidIndex() {
            assertDoesNotThrow(() -> ledger.register(-1, "Ghost", 100.00));
            assertDoesNotThrow(() -> ledger.register(10, "Ghost", 100.00));
        }

        @Test
        @DisplayName("empty slot should return null for name and 0 for balance")
        void emptySlotReturnsDefaults() {
            assertNull(ledger.getAccountHolder(5));
            assertEquals(0.0, ledger.getBalance(5), 0.01);
        }

    }

    @Nested
    @DisplayName("Highest Balance Tests")
    class HighestBalanceTests {

        @Test
        @DisplayName("should return name of account holder with highest balance")
        void highestBalanceName() {
            registerFourAccounts();
            assertEquals("Serge Alain Ndzié", ledger.highestBalanceName());
        }

        @Test
        @DisplayName("should return highest balance")
        void highestBalanceAmount() {
            registerFourAccounts();
            assertEquals(2_140_000.00, ledger.highestBalanceAmount());
        }

        @Test
        @DisplayName("should handle highest balance at first index")
        void highestAtFirstIndex() {
            ledger.register(0, "Richest", 999_999.00);
            ledger.register(1, "Second", 500.00);

            assertEquals("Richest", ledger.highestBalanceName());
            assertEquals(999_999.00, ledger.highestBalanceAmount(), 0.01);
        }

        @Test
        @DisplayName("should handle highest balance at last index")
        void highestAtLastIndex() {
            ledger.register(0, "First", 100.00);
            ledger.register(9, "Last", 50_000.00);

            assertEquals("Last", ledger.highestBalanceName());
        }

        @Test
        @DisplayName("should return null when no accounts registered")
        void highestBalanceNoAccounts() {
            assertNull(ledger.highestBalanceName());
        }

    }

    @Nested
    @DisplayName("Total Deposits Tests")
    class TotalDepositsTests {

        @Test
        @DisplayName("should compute total deposits when ledger is full")
        void totalDepositsFullLedger() {
            LOGGER.info("Print total account");
            registerAllAccounts();

            double expected = 0;
            for (double b : BALANCES) {
                expected += b;
            }

            assertEquals(expected, ledger.totalDeposits(), 0.01);
        }
    }

    @Nested
    @DisplayName("Print Ledger Tests")
    class PrintLedgerTests {

        @Test
        @DisplayName("should print all accounts")
        void printLedger() {
            registerAllAccounts();

            String[] result = ledger.printLedger();

            assertEquals(NAMES.length, result.length);
            assertTrue(result[0].contains(NAMES[0]));
            assertTrue(result[4].contains(NAMES[4]));
            assertTrue(result[9].contains(NAMES[9]));
            assertTrue(result[4].contains("5,000,000.00"));
        }
    }

}