package org.example.module1DataStructure.section1_coreBankingStorage.exercise1_3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ATMGrid {
    private static final Logger LOGGER = LoggerFactory.getLogger(ATMGrid.class);

    private static final int NUM_BRANCHES = 4;
    private static final int NUM_ATMS = 3;
    private static final String CURRENCY_FORMAT = "%,.2f";

    private double[][] atmCash = new double[NUM_BRANCHES][NUM_ATMS];

    public void loadCash(int branch, int atm, double amount) {
        if (branch < 0 || branch >= NUM_BRANCHES || atm < 0 || atm >= NUM_ATMS) {
            LOGGER.warn("Error: branch index or invalid ATM");
            return;
        }

        if (amount < 0) {
            LOGGER.warn("Error: the amount must be positive: {}", amount);
            return;
        }

        atmCash[branch][atm] = amount;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Loaded ${} to branch {}, ATM {}", String.format(CURRENCY_FORMAT, amount), branch, atm);
        }
    }

    public double getCash(int branch, int atm) {
        if (branch < 0 || branch >= NUM_BRANCHES || atm < 0 || atm >= NUM_ATMS) {
            LOGGER.warn("Invalid access: branch={}, atm={}", branch, atm);
            return 0;
        }
        return atmCash[branch][atm];
    }

    public String[][] printGrid() {
        String[] branches = {"A", "B", "C", "D"};
        String[][] result = new String[NUM_BRANCHES][NUM_ATMS];

        StringBuilder header = new StringBuilder(String.format("%-10s", ""));
        for (int j = 0; j < NUM_ATMS; j++) {
            header.append(String.format("%-15s", "ATM-" + (j + 1)));
        }
        LOGGER.info("ATM Cash Grid (Branch x ATM):");
        LOGGER.info("{}", header);

        for (int i = 0; i < NUM_BRANCHES; i++) {
            StringBuilder line = new StringBuilder();
            line.append(String.format("%-10s", "Branch " + branches[i] + ":"));

            for (int j = 0; j < NUM_ATMS; j++) {
                String formatted = String.format("$%,.2f", atmCash[i][j]);
                result[i][j] = formatted;
                line.append(String.format("%-15s", formatted));
            }

            LOGGER.info("{}", line);
        }

        return result;
    }

    public double branchTotal(int branch) {
        if (branch < 0 || branch >= NUM_BRANCHES) {
            LOGGER.warn("Invalid branch index: {}", branch);
            return 0;
        }

        double total = 0.0;
        for (int atm = 0; atm < NUM_ATMS; atm++) {
            total += atmCash[branch][atm];
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Total cash in branch {} is ${}", branch, String.format(CURRENCY_FORMAT, total));
        }
        return total;
    }

    public String[] findLowCash(double threshold) {
        String[] temp = new String[NUM_BRANCHES * NUM_ATMS];
        int count = 0;

        for (int i = 0; i < NUM_BRANCHES; i++) {
            for (int j = 0; j < NUM_ATMS; j++) {
                if (atmCash[i][j] < threshold) {
                    String formatted = String.format(CURRENCY_FORMAT, atmCash[i][j]);
                    temp[count++] = String.format("Branch %d, ATM %d: $%s", i, j, formatted);
                }
            }
        }

        String[] result = new String[count];
        System.arraycopy(temp, 0, result, 0, count);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Low cash alerts (< ${}):", String.format(CURRENCY_FORMAT, threshold));
            if (count == 0) {
                LOGGER.info("No ATMs below threshold");
            } else {
                for (String alert : result) {
                    LOGGER.warn("{}", alert);
                }
            }
        }

        return result;
    }
}
