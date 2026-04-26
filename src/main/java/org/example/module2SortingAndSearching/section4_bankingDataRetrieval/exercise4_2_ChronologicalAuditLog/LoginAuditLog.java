package org.example.module2SortingAndSearching.section4_bankingDataRetrieval.exercise4_2_ChronologicalAuditLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginAuditLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuditLog.class);
    private final Map<String, LoginEvent> log = new LinkedHashMap<>();

    public void logEvent(String sessionId, String accountNum, String timestamp, String ip, String status) {
        log.put(sessionId, new LoginEvent(accountNum, timestamp, ip, status));
        LOGGER.info("Logged: [{}] {} | {} | {} | {}", sessionId, accountNum, timestamp, ip, status);
    }

    public String[] printAuditTrail() {
        String[] result = new String[log.size()];
        int i = 0;
        for (Map.Entry<String, LoginEvent> entry : log.entrySet()) {
            LoginEvent e = entry.getValue();
            result[i] = String.format("[%s] %s | %s | %s | %s",
                    entry.getKey(), e.getAccountNumber(), e.getTimestamp(), e.getIpAddress(), e.getStatus());
            i++;
        }
        return result;
    }

    public String[] failedAttempts() {
        int count = 0;
        for (LoginEvent event : log.values()) {
            if ("FAILED".equals(event.getStatus())) {
                count++;
            }
        }

        String[] result = new String[count];
        int i = 0;
        for (Map.Entry<String, LoginEvent> entry : log.entrySet()) {
            if ("FAILED".equals(entry.getValue().getStatus())) {
                LoginEvent e = entry.getValue();
                result[i] = String.format("%s — %s from %s", entry.getKey(), e.getAccountNumber(), e.getIpAddress());
                i++;
            }
        }
        return result;
    }

    public int totalEvents() {
        return log.size();
    }
}
