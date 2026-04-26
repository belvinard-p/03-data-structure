package org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_3_SortedWealthReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

public class WealthReport {
    private static final Logger LOGGER = LoggerFactory.getLogger(WealthReport.class);
    private static final String CURRENCY_FORMAT = "$%,.2f";
    private final TreeMap<String, Double> accounts = new TreeMap<>();

    public void addAccount(String name, double balance) {
        accounts.put(name, balance);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Added: {} {}", name, String.format(CURRENCY_FORMAT, balance));
        }
    }

    public String[] printReport() {
        String[] result = new String[accounts.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            result[i] = String.format("%s %s", entry.getKey(), String.format(CURRENCY_FORMAT, entry.getValue()));
            i++;
        }
        return result;
    }

    public String richestCustomer() {
        if (accounts.isEmpty()) {
            LOGGER.warn("No accounts in report");
            return null;
        }

        String richest = null;
        double maxBalance = -1;
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            if (entry.getValue() > maxBalance) {
                maxBalance = entry.getValue();
                richest = entry.getKey();
            }
        }
        return richest;
    }

    public String[] customersAbove(double threshold) {
        int count = 0;
        for (double balance : accounts.values()) {
            if (balance > threshold) {
                count++;
            }
        }

        String[] result = new String[count];
        int i = 0;
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            if (entry.getValue() > threshold) {
                result[i] = String.format("%s %s", entry.getKey(), String.format(CURRENCY_FORMAT, entry.getValue()));
                i++;
            }
        }
        return result;
    }

    public int totalAccounts() {
        return accounts.size();
    }
}
