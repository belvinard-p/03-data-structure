package org.example.module1DataStructure.section2_dynamicBankingRecords.exercise2_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatementNavigator {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatementNavigator.class);
    private static final String CURRENCY_FORMAT = "$%,.2f";
    private StatementNode head;
    private StatementNode tail;
    private StatementNode cursor;

    public void addStatement(String month, double balance) {
        StatementNode newNode = new StatementNode(month, balance);

        if (head == null) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Added statement: {} | {}", month, String.format(CURRENCY_FORMAT, balance));
        }
    }

    public String current() {
        if (cursor == null) {
            return null;
        }
        return formatNode(cursor);
    }

    public String next() {
        if (cursor == null) {
            LOGGER.warn("No statements to navigate");
            return null;
        }
        if (cursor.next != null) {
            cursor = cursor.next;
        } else {
            LOGGER.info("Already at the most recent statement");
        }
        return current();
    }

    public String previous() {
        if (cursor == null) {
            LOGGER.warn("No statements to navigate");
            return null;
        }
        if (cursor.prev != null) {
            cursor = cursor.prev;
        } else {
            LOGGER.info("Already at the oldest statement");
        }
        return current();
    }

    public String[] printAll() {
        int count = 0;
        StatementNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        String[] result = new String[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            result[i] = formatNode(temp);
            temp = temp.next;
        }
        return result;
    }

    private String formatNode(StatementNode node) {
        return String.format("%s | " + CURRENCY_FORMAT, node.month, node.balance);
    }
}
