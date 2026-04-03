package splitwise.models;

public class ExpenseSplit {
    private final String userId;
    private final double share;

    public ExpenseSplit(String userId, double share) {
        this.userId = userId;
        this.share = share;
    }

    public String getUserId() {
        return userId;
    }

    public double getShare() {
        return share;
    }
}
