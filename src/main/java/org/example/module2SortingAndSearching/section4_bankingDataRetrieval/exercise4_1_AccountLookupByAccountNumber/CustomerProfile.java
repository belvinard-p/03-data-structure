package org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_1_AccountLookupByAccountNumber;

public class CustomerProfile {
    private final String name;
    private final String accountType;
    private final double balance;

    public CustomerProfile(String name, String accountType, double balance) {
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getName() { return name; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
}
