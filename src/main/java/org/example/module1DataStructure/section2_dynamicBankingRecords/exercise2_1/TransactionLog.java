package org.example.module1DataStructure.section2_dynamicBankingRecords.exercise2_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionLog.class);
    private TransactionNode head;

    public void addTransaction(String type, double amount, String date){
        TransactionNode newNode = new TransactionNode(type, amount, date);

        if (head == null) {
            head = newNode;
        }else {
            TransactionNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        LOGGER.info("Added transaction: {} ${} on {}", type, amount, date);
    }

    public void printHistory() {
        TransactionNode current = head;
        int index = 1;

        while (current != null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("[{}] {} ${} {}", index, current.type, String.format("%.2f", current.amount), current.date);
            }
            current = current.next;
            index++;
        }
    }

    public int countTransactions() {
        int count = 0;
        TransactionNode current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        LOGGER.info("Total transactions: {}", count);
        return count;
    }

    public double totalDeposited() {
        double total = 0;
        TransactionNode current = head;

        while (current != null) {
            if (current.type.equals("DEPOSIT")) {
                total += current.amount;
            }
            current = current.next;
        }

        LOGGER.info("Total deposited: ${}", String.format("%.2f", total));
        return total;
    }

}
