package org.example.module1DataStructure.section3_bankingOperationsProcessing.exercise3_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Queue;
import java.util.LinkedList;

public class PaymentQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentQueue.class);

    private Queue<Payment> paymentQueue;

    public PaymentQueue() {
        paymentQueue = new LinkedList<>();
    }

    public void submitPayment(String sender, String recipient, double amount) {
        paymentQueue.offer(new Payment(sender, recipient, amount));
        LOGGER.info("Submitted: {} → {} ${}", sender, recipient, String.format("%.2f", amount));
    }

    public String processNext() {
        if (paymentQueue.isEmpty()) {
            LOGGER.warn("No payments to process");
            return null;
        }

        Payment payment = paymentQueue.poll();
        String result = String.format("%s → %s $%.2f [CONFIRMED]",
                payment.getSenderName(), payment.getRecipientName(), payment.getAmount());
        LOGGER.info("Processing: {}", result);
        return result;
    }

    public String[] processAll() {
        int count = paymentQueue.size();
        String[] results = new String[count];
        for (int i = 0; i < count; i++) {
            results[i] = processNext();
        }
        return results;
    }

    public int pendingCount() {
        return paymentQueue.size();
    }
}