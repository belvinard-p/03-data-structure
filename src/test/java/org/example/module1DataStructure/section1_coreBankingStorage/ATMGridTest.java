package org.example.module1DataStructure.section1_coreBankingStorage;

import org.example.module1DataStructure.section1_coreBankingStorage.exercise1_3.ATMGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ATMGridTest {
    private ATMGrid atmGrid;

    @BeforeEach
    void setUp() {
        atmGrid = new ATMGrid();
    }

    @Test
    @DisplayName("Load Cash should correctly assign values to ATM")
    void loadCash() {
        atmGrid.loadCash(0, 0, 200);
        atmGrid.loadCash(1, 1, 300);
        atmGrid.loadCash(2, 2, 400);

        assertEquals(200, atmGrid.getCash(0, 0));
        assertEquals(300, atmGrid.getCash(1, 1));
        assertEquals(400, atmGrid.getCash(2, 2));
    }

    @Test
    @DisplayName("Should not update ATM when amount is negative")
    void loadCash_negativeAmount() {
        atmGrid.loadCash(0, 0, -100);

        assertEquals(0, atmGrid.getCash(0, 0));
    }

    @Test
    @DisplayName("Should not update ATM when index is invalid")
    void loadCash_invalidIndex() {
        atmGrid.loadCash(5, 0, 100);

        assertEquals(0, atmGrid.getCash(0, 0));
    }

    @Test
    @DisplayName("Print Grid should correctly print the grid")
    void printGrid() {
        atmGrid.loadCash(0, 0, 500_000);
        atmGrid.loadCash(0, 1, 250_000);
        atmGrid.loadCash(1, 0, 100_000);
        atmGrid.loadCash(2, 1, 750_000);

        String[][] result = atmGrid.printGrid();

        assertEquals(4, result.length);
        assertEquals(3, result[0].length);
        assertEquals("$500,000.00", result[0][0]);
        assertEquals("$250,000.00", result[0][1]);
        assertEquals("$100,000.00", result[1][0]);
        assertEquals("$0.00", result[1][1]);
        assertEquals("$0.00", result[2][0]);
        assertEquals("$750,000.00", result[2][1]);
    }

    @Test
    @DisplayName("Branch Total should calculate total cash for a branch")
    void branchTotal() {
        atmGrid.loadCash(0, 0, 500_000);
        atmGrid.loadCash(0, 1, 250_000);
        atmGrid.loadCash(0, 2, 100_000);

        assertEquals(850_000, atmGrid.branchTotal(0), 0.01);
    }

    @Test
    @DisplayName("Find Low Cash should return ATMs below threshold")
    void findLowCash() {
        atmGrid.loadCash(0, 0, 500_000);
        atmGrid.loadCash(1, 1, 50_000);
        atmGrid.loadCash(2, 0, 200_000);

        String[] result = atmGrid.findLowCash(100_000);

        // 12 ATMs total, 2 above threshold (500k, 200k) = 10 below
        assertEquals(10, result.length);
        assertTrue(result[0].contains("Branch 0, ATM 1"));
        assertTrue(result[0].contains("0.00"));
    }

    @Test
    @DisplayName("Find Low Cash should return empty array when all above threshold")
    void findLowCash_noneBelow() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                atmGrid.loadCash(i, j, 500_000);
            }
        }

        String[] result = atmGrid.findLowCash(100_000);

        assertEquals(0, result.length);
    }
}
