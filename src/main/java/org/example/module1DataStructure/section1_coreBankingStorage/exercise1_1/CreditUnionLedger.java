package org.example.module1DataStructure.section1_coreBankingStorage.exercise1_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditUnionLedger {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditUnionLedger.class);
    private static final int MAX_ACCOUNTS = 10;
    private static final String CURRENCY_FORMAT = "%,.2f";

    private final String[] accountHolders = new String[MAX_ACCOUNTS];
    private final double[] balances = new double[MAX_ACCOUNTS];
    private int accountCount = 0;

    public void register(int index, String name, double balance) {
        if (index < 0 || index >= MAX_ACCOUNTS) {
            LOGGER.warn("Invalid index: {}. Must be 0–{}", index, MAX_ACCOUNTS - 1);
            return;
        }

        if (balance < 0) {
            LOGGER.warn("Balance must be positive: {}", balance);
            return;
        }

        accountHolders[index] = name;
        balances[index] = balance;
        accountCount++;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Registered [{}] {} — ${}", index, name, String.format(CURRENCY_FORMAT, balance));
        }
    }

    public double totalDeposits() {
        double total = 0;
        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            total += balances[i];
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Total deposits: ${}", String.format(CURRENCY_FORMAT, total));
        }
        return total;
    }

    public String highestBalanceName() {
        int maxIndex = 0;
        for (int i = 1; i < MAX_ACCOUNTS; i++) {
            if (balances[i] > balances[maxIndex]) {
                maxIndex = i;
            }
        }

        if (accountHolders[maxIndex] == null) {
            return null;
        }
        return accountHolders[maxIndex];
    }

    public double highestBalanceAmount() {
        int maxIndex = 0;
        for (int i = 1; i < MAX_ACCOUNTS; i++) {
            if (balances[i] > balances[maxIndex]) {
                maxIndex = i;
            }
        }

        return balances[maxIndex];
    }

    public String getAccountHolder(int index) {
        return accountHolders[index];
    }

    public double getBalance(int index) {
        return balances[index];
    }

    public String[] printLedger() {
        LOGGER.info("=== Credit Union Ledger ===");
        String[] result = new String[accountCount];
        int index = 0;
        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            if (accountHolders[i] != null) {
                String formatted = String.format(CURRENCY_FORMAT, balances[i]);
                result[index++] = String.format("[%d] %s $%s", i, accountHolders[i], formatted);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("[{}] {} ${}", i, accountHolders[i], formatted);
                }
            }
        }

        return result;
    }
}
