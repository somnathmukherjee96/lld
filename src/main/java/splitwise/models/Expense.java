package splitwise.models;

import splitwise.enums.SplitType;

import java.time.LocalDateTime;
import java.util.List;

public class Expense {
    private final String expenseId;
    private final SplitType splitType;
    private final String paidBy;
    private final double amount;
    private final String description;
    private final List<ExpenseSplit> splits;
    private final LocalDateTime createdAt;

    public Expense(ExpenseBuilder builder) {
        this.expenseId = builder.expenseId;
        this.splitType = builder.splitType;
        this.paidBy = builder.paidBy;
        this.amount = builder.amount;
        this.description = builder.description;
        this.splits = builder.splits;
        this.createdAt = builder.createdAt;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public List<ExpenseSplit> getSplits() {
        return splits;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static class ExpenseBuilder {
        private String expenseId;
        private SplitType splitType;
        private String paidBy;
        private double amount;
        private String description;
        private List<ExpenseSplit> splits;
        private LocalDateTime createdAt;

        public ExpenseBuilder expenseId(String expenseId) {
            this.expenseId = expenseId;
            return this;
        }

        public ExpenseBuilder splitType(SplitType splitType) {
            this.splitType = splitType;
            return this;
        }

        public ExpenseBuilder paidBy(String user) {
            this.paidBy = user;
            return this;
        }

        public ExpenseBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public ExpenseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder splits(List<ExpenseSplit> splits) {
            this.splits = splits;
            return this;
        }

        public ExpenseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Expense build(){
            return new Expense(this);
        }
    }
}
