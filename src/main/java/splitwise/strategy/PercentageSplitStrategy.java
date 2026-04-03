package splitwise.strategy;

import splitwise.models.ExpenseSplit;
import splitwise.models.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public List<ExpenseSplit> calculateSplit(List<User> participants, double amount, List<Double> splitValues) {
        double totalPercentage = splitValues.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        if (Math.abs(100 - totalPercentage) > 0.01)
            throw new IllegalArgumentException("Split percentage doesn't adds upto 100% !");

        List<ExpenseSplit> expenseSplits = new ArrayList<>();
        for (int i = 0; i < participants.size(); i++)
            expenseSplits.add(new ExpenseSplit(participants.get(i).getUserId(), amount / splitValues.get(i)));

        return expenseSplits;
    }
}
