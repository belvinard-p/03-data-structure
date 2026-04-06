package org.example.module1DataStructure.section2_dynamicBankingRecords.exercise2_1;

public class TransactionNode {
    String type;
    double amount;
    String date;
    TransactionNode next;

    public TransactionNode(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
}
