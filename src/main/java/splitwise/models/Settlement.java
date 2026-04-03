package splitwise.models;

import java.time.LocalDateTime;

public class Settlement {
    private final String id;
    private final String paidTo;
    private final String paidBy;
    private final double amount;
    private final LocalDateTime timestamp;

    public Settlement(String id, String paidTo, String paidBy, double amount, LocalDateTime timestamp) {
        this.id = id;
        this.paidTo = paidTo;
        this.paidBy = paidBy;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
