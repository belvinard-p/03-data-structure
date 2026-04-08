package org.example.module1DataStructure.section3_bankingOperationsProcessing;

import org.example.module1DataStructure.section3_bankingOperationsProcessing.exercise3_2.PaymentQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentQueueTest {

    PaymentQueue queue;

    @BeforeEach
    void setUp() {
        
        queue = new PaymentQueue();
    }

    @Test
    @DisplayName("Pending count is 0 on empty queue")
    void pendingCountEmpty() {
        
        assertEquals(0, queue.pendingCount());
    }

    @Test
    @DisplayName("Submit increases pending count")
    void submitPayment() {
        queue.submitPayment("Tatchoua", "Samy", 250.0);
        queue.submitPayment("Samy", "James", 1000.0);
        queue.submitPayment("James", "Tatchoua", 75.0);

        assertEquals(3, queue.pendingCount());
    }

    @Test
    @DisplayName("ProcessNext returns first submitted payment")
    void processNextFIFO() {
        queue.submitPayment("Tatchoua", "Samy", 250.0);
        queue.submitPayment("Samy", "James", 1000.0);

        String result = queue.processNext();
        assertTrue(result.contains("Tatchoua"));
        assertTrue(result.contains("Samy"));
        assertTrue(result.contains("250"));
        assertEquals(1, queue.pendingCount());
    }

    @Test
    @DisplayName("ProcessNext on empty queue returns null")
    void processNextEmpty() {
        
        assertNull(queue.processNext());
    }

    @Test
    @DisplayName("ProcessAll processes every payment in FIFO order")
    void processAll() {
        queue.submitPayment("Tatchoua", "Samy", 250.0);
        queue.submitPayment("Samy", "James", 1000.0);
        queue.submitPayment("James", "Tatchoua", 75.0);

        String[] results = queue.processAll();
        assertEquals(3, results.length);
        assertTrue(results[0].contains("Tatchoua"));
        assertTrue(results[1].contains("Samy"));
        assertTrue(results[2].contains("James"));
        assertEquals(0, queue.pendingCount());
    }

    @Test
    @DisplayName("ProcessAll on empty queue returns empty array")
    void processAllEmpty() {
        String[] results = queue.processAll();
        assertEquals(0, results.length);
    }
}
