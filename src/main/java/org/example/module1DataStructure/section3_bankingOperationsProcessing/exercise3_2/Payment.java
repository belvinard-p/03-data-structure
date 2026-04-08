package org.example.module1DataStructure.section3_bankingOperationsProcessing.exercise3_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Payment {
    private static final Logger LOGGER = LoggerFactory.getLogger(Payment.class);

    private String senderName;
    private String recipientName;
    private double amount;

    public Payment(String senderName, String recipientName, double amount) {
        this.senderName = senderName;
        this.recipientName = recipientName;
        this.amount = amount;
    }

    public String getSenderName() {

        return senderName;
    }


    public String getRecipientName() {

        return recipientName;
    }

    public double getAmount() {

        return amount;
    }

}
