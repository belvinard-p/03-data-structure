package org.example.module1DataStructure.section1_coreBankingStorage;

import org.example.module1DataStructure.section1_coreBankingStorage.exercise1_2.CustomerOnboarding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerOnboardingTest {

    private CustomerOnboarding onboarding;

    @BeforeEach
    void setUp() {
        onboarding = new CustomerOnboarding();
    }

    @Test
    void testAddCustomer() {
        onboarding.addCustomer("Pouadjeu");
        assertTrue(onboarding.isRegistered("Pouadjeu"));
        assertEquals(1, onboarding.getQueueSize());
    }

    @Test
    void testRemoveCustomer() {
        onboarding.addCustomer("Pouadjeu");
        onboarding.addCustomer("Belvi");

        onboarding.removeCustomer("Pouadjeu");
        assertFalse(onboarding.isRegistered("Pouadjeu"));
        assertTrue(onboarding.isRegistered("Belvi"));
        assertEquals(1, onboarding.getQueueSize());
    }

    @Test
    void testIsRegistered() {
        onboarding.addCustomer("Belvi");
        assertTrue(onboarding.isRegistered("Belvi"));
        assertFalse(onboarding.isRegistered("Unknown"));
    }

    @Test
    void testPrintQueue() {
        onboarding.addCustomer("Pouadjeu");
        onboarding.addCustomer("Belvi");
        onboarding.printQueue();
    }
}
