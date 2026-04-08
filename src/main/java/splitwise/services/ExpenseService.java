package splitwise.services;

import splitwise.enums.SplitType;
import splitwise.models.Expense;
import splitwise.models.Group;
import splitwise.observer.ExpenseObservable;
import splitwise.observer.ExpenseObserver;
import splitwise.strategy.EqualSplitStrategy;
import splitwise.strategy.ExactSplitStrategy;
import splitwise.strategy.PercentageSplitStrategy;
import splitwise.strategy.SplitStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ExpenseService implements ExpenseObservable {
    private final BalanceService balanceService;
    private final GroupService groupService;
    private final SettlementService settlementService;
    private final UserService userService;
    private final List<ExpenseObserver> observers;

    private final Map<SplitType, SplitStrategy> strategyMap = Map.of(
            SplitType.EQUAL, new EqualSplitStrategy(),
            SplitType.EXACT, new ExactSplitStrategy(),
            SplitType.PERCENTAGE, new PercentageSplitStrategy());

    public ExpenseService(BalanceService balanceService, GroupService groupService, SettlementService settlementService, UserService userService) {
        this.balanceService = balanceService;
        this.groupService = groupService;
        this.settlementService = settlementService;
        this.userService = userService;
        this.observers = Collections.synchronizedList(new ArrayList<>());
    }

    public Expense createExpense(String expenseId, SplitType splitType, String paidBy, double amount, String description, List<String> participants, List<Double> splitValues) {
        SplitStrategy strategy = strategyMap.get(splitType);
        if (strategy == null)
            throw new IllegalArgumentException("Unsupported split type : " + splitType);

        return new Expense.ExpenseBuilder()
                .expenseId(expenseId)
                .splitType(splitType)
                .paidBy(paidBy)
                .amount(amount)
                .description(description)
                .splits(strategy.calculateSplit(participants, amount, splitValues))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void addExpense(String groupId, String expenseId, SplitType splitType, String paidBy, double amount, String description, List<Double> splitValues) {
        Group group = groupService.getGroupById(groupId);
        List<String> participants = group.getMembers();
        if (!participants.contains(paidBy))
            throw new IllegalArgumentException(paidBy + " is not part of this group.");

        Expense expense = createExpense(expenseId, splitType, paidBy, amount, description, participants, splitValues);
        group.addExpense(expense);
        balanceService.updateBalanceForExpense(group, expense);
        notifyExpenseAdded(expense);
    }


    @Override
    public void addObservers(ExpenseObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ExpenseObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyExpenseAdded(Expense expense) {
        observers.forEach(observer -> observer.onExpenseAdded(expense));
    }
}
