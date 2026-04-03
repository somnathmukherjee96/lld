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
    private final List<User> members;
    private final String createdBy;
    private final Map<String, Double> balance = new HashMap<>();
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

    public List<User> getMembers() {
        return members;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Map<String, Double> getBalance() {
        return balance;
    }

    public boolean isOneOnOne() {
        return isOneOnOne;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addBalance(String userId, double amount) {
        balance.put(userId, amount);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public static class GroupBuilder {
        private String groupId;
        private String groupName = "";
        private boolean isOneOnOne = false;
        private List<User> members;
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

        public GroupBuilder members(List<User> members) {
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
