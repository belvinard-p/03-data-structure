package org.example.module1DataStructure.section2_dynamicBankingRecords.exercise2_2;

public class StatementNode {
    String month;
    double balance;
    StatementNode next;
    StatementNode prev;

    public StatementNode(String month, double balance) {
        this.month = month;
        this.balance = balance;
    }
}
