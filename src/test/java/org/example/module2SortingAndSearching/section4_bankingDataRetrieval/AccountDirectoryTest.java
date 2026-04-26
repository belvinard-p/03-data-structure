package org.example.module2SortingAndSearching.section4_bankingDataRetrieval;

import org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_1_AccountLookupByAccountNumber.AccountDirectory;
import org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_1_AccountLookupByAccountNumber.CustomerProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDirectoryTest {

    AccountDirectory directory;

    @BeforeEach
    void setUp() {
        directory = new AccountDirectory();
        directory.openAccount("ACC-1001", "Jean Pierre Ngono", "SAVINGS", 5000.0);
        directory.openAccount("ACC-1002", "Brice Kamdem", "CURRENT", 12000.0);
    }

    // === openAccount ===

    @Test
    @DisplayName("Open account increases total customers")
    void openAccount() {
        directory.openAccount("ACC-1003", "Serge Alain Ndzié", "SAVINGS", 8000.0);
        assertEquals(3, directory.totalCustomers());
    }

    @Test
    @DisplayName("Open duplicate account is rejected")
    void openDuplicateAccount() {
        assertFalse(directory.openAccount("ACC-1001", "Other Name", "CURRENT", 100.0));
        assertEquals(2, directory.totalCustomers());
    }

    // === lookup ===

    @Test
    @DisplayName("Lookup existing account returns profile")
    void lookupExisting() {
        CustomerProfile profile = directory.lookup("ACC-1001");
        assertNotNull(profile);
        assertEquals("Jean Pierre Ngono", profile.getName());
        assertEquals("SAVINGS", profile.getAccountType());
        assertEquals(5000.0, profile.getBalance(), 0.01);
    }

    @Test
    @DisplayName("Lookup non-existing account returns null")
    void lookupNonExisting() {
        assertNull(directory.lookup("ACC-9999"));
    }

    // === closeAccount ===

    @Test
    @DisplayName("Close existing account removes it")
    void closeAccount() {
        assertTrue(directory.closeAccount("ACC-1002"));
        assertEquals(1, directory.totalCustomers());
        assertNull(directory.lookup("ACC-1002"));
    }

    @Test
    @DisplayName("Close non-existing account returns false")
    void closeNonExisting() {
        assertFalse(directory.closeAccount("ACC-9999"));
        assertEquals(2, directory.totalCustomers());
    }

    // === totalCustomers ===

    @Test
    @DisplayName("Total customers on empty directory is 0")
    void totalCustomersEmpty() {
        AccountDirectory empty = new AccountDirectory();
        assertEquals(0, empty.totalCustomers());
    }
}
