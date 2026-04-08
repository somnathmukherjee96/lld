package splitwise.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    private final String groupId;
    private final String groupName;
    private final boolean isOneOnOne;
    private final List<String> members;
    private final String createdBy;
    private final Map<String, Balance> balances = new HashMap<>();
    private final List<Expense> expenses = new ArrayList<>();
    private final LocalDateTime createdAt;

    public Group(GroupBuilder builder) {
        this.groupId = builder.groupId;
        this.groupName = builder.groupName;
        this.isOneOnOne = builder.isOneOnOne;
        this.members = builder.members;
        this.createdAt = builder.createdAt;
        this.createdBy = builder.createdBy;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getMembers() {
        return members;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Map<String, Balance> getBalances() {
        return balances;
    }

    public boolean isOneOnOne() {
        return isOneOnOne;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public static String balanceKey(String fromId, String toId) {
        return fromId.compareTo(toId) < 0
                ? fromId + ":" + toId
                : toId + ":" + fromId;
    }

    public void updateBalnce(String fromId, String toId, double amount) {
        String key = balanceKey(fromId, toId);

        Balance balance = balances.computeIfAbsent(key, k -> new Balance(fromId, toId, amount));

        if (balance.getFromUser().equals(fromId))
            balance.addAmount(amount);
        else
            balance.subtractAmount(amount);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public static class GroupBuilder {
        private String groupId;
        private String groupName = "";
        private boolean isOneOnOne = false;
        private List<String> members;
        private String createdBy;
        private LocalDateTime createdAt = LocalDateTime.now();

        public GroupBuilder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public GroupBuilder groupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public GroupBuilder isOneOnOne(boolean isOneOnOne) {
            this.isOneOnOne = isOneOnOne;
            return this;
        }

        public GroupBuilder members(List<String> members) {
            this.members = members;
            return this;
        }

        public GroupBuilder createdBy(String userId) {
            this.createdBy = userId;
            return this;
        }

        public GroupBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }
}
