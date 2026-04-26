package org.example.module2SortingAndSearching.section4_bankingDataRetrieval;

import org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_2_ChronologicalAuditLog.LoginAuditLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginAuditLogTest {

    LoginAuditLog auditLog;

    @BeforeEach
    void setUp() {
        auditLog = new LoginAuditLog();
        auditLog.logEvent("SID-001", "ACC-1001", "2025-03-15 09:01", "192.168.1.10", "SUCCESS");
        auditLog.logEvent("SID-002", "ACC-1003", "2025-03-15 09:04", "10.0.0.55", "FAILED");
        auditLog.logEvent("SID-003", "ACC-1001", "2025-03-15 09:12", "192.168.1.10", "SUCCESS");
        auditLog.logEvent("SID-004", "ACC-1003", "2025-03-15 09:14", "10.0.0.55", "FAILED");
    }

    // === logEvent + totalEvents ===

    @Test
    @DisplayName("Total events after logging")
    void totalEvents() {
        assertEquals(4, auditLog.totalEvents());
    }

    @Test
    @DisplayName("Empty log has 0 events")
    void totalEventsEmpty() {
        LoginAuditLog empty = new LoginAuditLog();
        assertEquals(0, empty.totalEvents());
    }

    // === printAuditTrail ===

    @Test
    @DisplayName("Audit trail preserves chronological insertion order")
    void printAuditTrailOrder() {
        String[] trail = auditLog.printAuditTrail();
        assertEquals(4, trail.length);
        assertTrue(trail[0].contains("SID-001"));
        assertTrue(trail[1].contains("SID-002"));
        assertTrue(trail[2].contains("SID-003"));
        assertTrue(trail[3].contains("SID-004"));
    }

    @Test
    @DisplayName("Audit trail contains event details")
    void printAuditTrailContent() {
        String[] trail = auditLog.printAuditTrail();
        assertTrue(trail[0].contains("ACC-1001"));
        assertTrue(trail[0].contains("192.168.1.10"));
        assertTrue(trail[0].contains("SUCCESS"));
    }

    @Test
    @DisplayName("Empty audit trail returns empty array")
    void printAuditTrailEmpty() {
        LoginAuditLog empty = new LoginAuditLog();
        assertEquals(0, empty.printAuditTrail().length);
    }

    // === failedAttempts ===

    @Test
    @DisplayName("Failed attempts returns only FAILED events")
    void failedAttempts() {
        String[] failed = auditLog.failedAttempts();
        assertEquals(2, failed.length);
        assertTrue(failed[0].contains("SID-002"));
        assertTrue(failed[1].contains("SID-004"));
    }

    @Test
    @DisplayName("Failed attempts contain account and IP")
    void failedAttemptsContent() {
        String[] failed = auditLog.failedAttempts();
        assertTrue(failed[0].contains("ACC-1003"));
        assertTrue(failed[0].contains("10.0.0.55"));
    }

    @Test
    @DisplayName("No failed attempts returns empty array")
    void failedAttemptsNone() {
        LoginAuditLog clean = new LoginAuditLog();
        clean.logEvent("SID-100", "ACC-1001", "2025-03-15 10:00", "192.168.1.10", "SUCCESS");
        assertEquals(0, clean.failedAttempts().length);
    }
}
