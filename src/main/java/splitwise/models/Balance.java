package splitwise.models;

public class Balance {
    private final String fromUser;
    private final String toUser;
    private final double amount;

    public Balance(String fromUser, String toUser, double amount) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public double getAmount() {
        return amount;
    }
}
