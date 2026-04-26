package org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_2_ChronologicalAuditLog;

public class LoginEvent {
    private final String accountNumber;
    private final String timestamp;
    private final String ipAddress;
    private final String status;

    public LoginEvent(String accountNumber, String timestamp, String ipAddress, String status) {
        this.accountNumber = accountNumber;
        this.timestamp = timestamp;
        this.ipAddress = ipAddress;
        this.status = status;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getTimestamp() { return timestamp; }
    public String getIpAddress() { return ipAddress; }
    public String getStatus() { return status; }
}
