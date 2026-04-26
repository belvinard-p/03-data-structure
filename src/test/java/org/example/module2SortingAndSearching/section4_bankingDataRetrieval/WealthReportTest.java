package org.example.module2SortingAndSearching.section4_bankingDataRetrieval;

import org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_3_SortedWealthReport.WealthReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WealthReportTest {

    WealthReport report;

    @BeforeEach
    void setUp() {
        report = new WealthReport();
        report.addAccount("David Mbeki", 3750.0);
        report.addAccount("Alice Marais", 5200.0);
        report.addAccount("Carmen Liu", 91000.0);
        report.addAccount("Ben Okafor", 14000.0);
    }

    // === printReport ===

    @Test
    @DisplayName("Report is sorted alphabetically by name")
    void printReportAlphabeticalOrder() {
        String[] result = report.printReport();
        assertEquals(4, result.length);
        assertTrue(result[0].contains("Alice Marais"));
        assertTrue(result[1].contains("Ben Okafor"));
        assertTrue(result[2].contains("Carmen Liu"));
        assertTrue(result[3].contains("David Mbeki"));
    }

    @Test
    @DisplayName("Report contains formatted balances")
    void printReportContainsBalances() {
        String[] result = report.printReport();
        assertTrue(result[0].contains("$5,200.00"));
        assertTrue(result[2].contains("$91,000.00"));
    }

    @Test
    @DisplayName("Empty report returns empty array")
    void printReportEmpty() {
        WealthReport empty = new WealthReport();
        assertEquals(0, empty.printReport().length);
    }

    // === richestCustomer ===

    @Test
    @DisplayName("Richest customer is Carmen Liu")
    void richestCustomer() {
        assertEquals("Carmen Liu", report.richestCustomer());
    }

    @Test
    @DisplayName("Richest customer on empty report returns null")
    void richestCustomerEmpty() {
        WealthReport empty = new WealthReport();
        assertNull(empty.richestCustomer());
    }

    // === customersAbove ===

    @Test
    @DisplayName("Customers above 10000 returns Ben and Carmen")
    void customersAboveThreshold() {
        String[] result = report.customersAbove(10000.0);
        assertEquals(2, result.length);
        assertTrue(result[0].contains("Ben Okafor"));
        assertTrue(result[1].contains("Carmen Liu"));
    }

    @Test
    @DisplayName("Customers above very high threshold returns empty")
    void customersAboveNone() {
        String[] result = report.customersAbove(100000.0);
        assertEquals(0, result.length);
    }

    // === totalAccounts ===

    @Test
    @DisplayName("Total accounts after adding")
    void totalAccounts() {
        assertEquals(4, report.totalAccounts());
    }
}
