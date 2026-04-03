package splitwise.services;

import splitwise.enums.SplitType;
import splitwise.models.Expense;
import splitwise.models.Group;
import splitwise.strategy.SplitStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class ExpenseService {
    private final BalanceService balanceService;
    private final GroupService groupService;
    private final SettlementService settlementService;
    private final UserService userService;
    private final SplitStrategy splitStrategy;

    public ExpenseService(BalanceService balanceService, GroupService groupService, SettlementService settlementService, UserService userService, SplitStrategy splitStrategy) {
        this.balanceService = balanceService;
        this.groupService = groupService;
        this.settlementService = settlementService;
        this.userService = userService;
        this.splitStrategy = splitStrategy;
    }

    public Expense createExpense(String expenseId, SplitType splitType, String paidBy, double amount, String description, List<String> participants, List<Double> splitValues) {
        return new Expense.ExpenseBuilder()
                .expenseId(expenseId)
                .splitType(splitType)
                .paidBy(paidBy)
                .amount(amount)
                .description(description)
                .splits(splitStrategy.calculateSplit(participants, amount, splitValues))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void addExpense(String groupId, String expenseId, SplitType splitType, String paidBy, double amount, String description, List<Double> splitValues) {
        Group group = groupService.getGroupById(groupId);
        List<String> participants = group.getMembers();
        Expense expense = createExpense(expenseId, splitType, paidBy, amount, description, participants, splitValues);
        group.addExpense(expense);
    }


}
