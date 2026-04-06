package org.example.module1DataStructure.section2_dynamicBankingRecords;

import org.example.module1DataStructure.section2_dynamicBankingRecords.exercise2_2.StatementNavigator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatementNavigatorTest {

    StatementNavigator navigator;

    @BeforeEach
    void setUp() {
        navigator = new StatementNavigator();
        navigator.addStatement("January 2025", 3200.0);
        navigator.addStatement("February 2025", 4800.0);
        navigator.addStatement("March 2025", 3950.0);
    }

    @Test
    @DisplayName("Current returns first statement after adding")
    void currentReturnsFirst() {
        assertEquals("January 2025 | $3,200.00", navigator.current());
    }

    @Test
    @DisplayName("Next moves to February")
    void nextMovesForward() {
        assertEquals("February 2025 | $4,800.00", navigator.next());
    }

    @Test
    @DisplayName("Next twice moves to March")
    void nextTwiceMovesToMarch() {
        navigator.next();
        assertEquals("March 2025 | $3,950.00", navigator.next());
    }

    @Test
    @DisplayName("Next at end stays at last statement")
    void nextAtEndStays() {
        navigator.next();
        navigator.next();
        assertEquals("March 2025 | $3,950.00", navigator.next());
    }

    @Test
    @DisplayName("Previous after next returns to February")
    void previousMovesBackward() {
        navigator.next();
        navigator.next();
        assertEquals("February 2025 | $4,800.00", navigator.previous());
    }

    @Test
    @DisplayName("Previous at head stays at first statement")
    void previousAtHeadStays() {
        assertEquals("January 2025 | $3,200.00", navigator.previous());
    }

    @Test
    @DisplayName("PrintAll returns all statements oldest to newest")
    void printAllReturnsAll() {
        String[] result = navigator.printAll();
        assertEquals(3, result.length);
        assertEquals("January 2025 | $3,200.00", result[0]);
        assertEquals("February 2025 | $4,800.00", result[1]);
        assertEquals("March 2025 | $3,950.00", result[2]);
    }

    @Test
    @DisplayName("Current returns null on empty navigator")
    void currentOnEmptyReturnsNull() {
        StatementNavigator empty = new StatementNavigator();
        assertNull(empty.current());
    }

    @Test
    @DisplayName("PrintAll returns empty array on empty navigator")
    void printAllEmptyReturnsEmptyArray() {
        StatementNavigator empty = new StatementNavigator();
        assertEquals(0, empty.printAll().length);
    }
}
