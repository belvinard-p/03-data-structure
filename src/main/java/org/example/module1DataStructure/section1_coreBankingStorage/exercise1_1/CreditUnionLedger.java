package org.example.module1DataStructure.section1_coreBankingStorage.exercise1_1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditUnionLedger {
    private static final Logger LOGGER = Logger.getLogger(CreditUnionLedger.class.getName());
    private static final int MAX_ACCOUNTS = 10;

    private final String[] accountHolders = new String[MAX_ACCOUNTS];
    private final double[] balances = new double[MAX_ACCOUNTS];
    private int accountCount = 0;

    public void register(int index, String name, double balance) {
        if(index < 0 || index >= MAX_ACCOUNTS){
            LOGGER.log(Level.WARNING, "Invalid index: {0}. Must be 0–{1}",  new Object[]{index, MAX_ACCOUNTS - 1});
            return;
        }

        accountHolders[index] = name;
        balances[index] = balance;
        accountCount++;
        LOGGER.log(Level.INFO, "Registered [{0}] {1} — ${2}", new Object[]{index, name, String.format("%,.2f", balance)});
    }

    public double totalDeposits() {
        double total = 0;
        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            total += balances[i];
        }
        return total;
    }


    public String highestBalanceName() {
        int maxIndex = 0;
        for (int i = 1; i < MAX_ACCOUNTS; i++) {
            if(balances[i] > balances[maxIndex]) {
                maxIndex = i;
            }
        }

        if(accountHolders[maxIndex] == null) {
            return null;
        }
        return accountHolders[maxIndex];
    }


    public double highestBalanceAmount() {
        int maxIndex = 0;
        for (int i = 1; i < MAX_ACCOUNTS; i++) {
            if(balances[i] > balances[maxIndex]) {
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
                result[index++] = String.format("[%d] %s $%,.2f", i, accountHolders[i], balances[i]);
                LOGGER.log(Level.INFO, "{0}", result[index - 1]);
            }
        }

        return result;
    }
}
