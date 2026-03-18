package org.example.module1DataStructure.section1_coreBankingStorage.exercise1_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CustomerOnboarding {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOnboarding.class);

    ArrayList<String> customerNames  = new ArrayList<>();

    public void addCustomer(String name) {
        if (name == null || name.trim().isEmpty()) {
            LOGGER.warn("Cannot add null or empty name");
            return;
        }

        if (customerNames.contains(name)) {
            LOGGER.info("Customer '{}' is already in the list", name);
            return;
        }

        customerNames.add(name);
        LOGGER.info("Customer '{}' added successfully", name);
    }

    public void removeCustomer(String name) {
        if (name == null || name.trim().isEmpty()) {
            LOGGER.warn("Cannot remove null or empty name");
            return;
        }

        boolean removed = customerNames.remove(name);
        if (removed) {
            LOGGER.info("Customer '{}' removed successfully", name);
        } else {
            LOGGER.warn("Customer '{}' not found in the list", name);
        }
    }

    public boolean isRegistered(String name) {
        if (name == null || name.trim().isEmpty()) {
            LOGGER.warn("Cannot check for null or empty name");
            return false;
        }

        return customerNames.contains(name);
    }

    public void printQueue() {
        if (customerNames.isEmpty()) {
            LOGGER.warn("The customer queue is empty");
            LOGGER.info("No customers in the queue.");
            return;
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("\n=== Customer Onboarding Queue ===");
            for (int i = 0; i < customerNames.size(); i++) {
                LOGGER.info("{}. {}", i + 1, customerNames.get(i));
            }
            LOGGER.info("Total customers: {}", customerNames.size());
        }
    }

    public int getQueueSize() {
        return customerNames.size();
    }

}
