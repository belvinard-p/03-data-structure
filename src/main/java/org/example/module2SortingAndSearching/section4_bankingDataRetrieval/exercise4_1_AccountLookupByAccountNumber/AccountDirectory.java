package org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_1_AccountLookupByAccountNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AccountDirectory {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDirectory.class);

    private final Map<String, CustomerProfile> accounts = new HashMap<>();

    public boolean openAccount(String accountNum, String name, String type, double balance) {
        if (accounts.containsKey(accountNum)) {
            LOGGER.warn("Account already exists: {}", accountNum);
            return false;
        }
        accounts.put(accountNum, new CustomerProfile(name, type, balance));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Account opened: {} | {} | {} | ${}", accountNum, name, type, String.format("%.2f", balance));
        }
        return true;
    }

    public CustomerProfile lookup(String accountNum) {
        CustomerProfile profile = accounts.get(accountNum);
        if (profile == null) {
            LOGGER.warn("Account not found: {}", accountNum);
        }
        return profile;
    }

    public boolean closeAccount(String accountNum) {
        CustomerProfile removed = accounts.remove(accountNum);
        if (removed == null) {
            LOGGER.warn("Account not found: {}", accountNum);
            return false;
        }
        LOGGER.info("Account closed: {} | {}", accountNum, removed.getName());
        return true;
    }

    public int totalCustomers() {
        return accounts.size();
    }
}
