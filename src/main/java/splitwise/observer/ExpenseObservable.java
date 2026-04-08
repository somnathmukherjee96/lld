package splitwise.observer;

import splitwise.models.Expense;

public interface ExpenseObservable {
    void addObservers(ExpenseObserver observer);
    void removeObserver(ExpenseObserver observer);
    void notifyExpenseAdded(Expense expense);
}
