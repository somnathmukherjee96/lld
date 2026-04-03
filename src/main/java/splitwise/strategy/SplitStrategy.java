package splitwise.strategy;

import splitwise.models.ExpenseSplit;
import splitwise.models.User;

import java.util.List;

public interface SplitStrategy {
    List<ExpenseSplit> calculateSplit(List<String> participants, double amount, List<Double> splitValues);
}
