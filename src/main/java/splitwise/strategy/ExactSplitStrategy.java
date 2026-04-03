package splitwise.strategy;

import splitwise.models.ExpenseSplit;
import splitwise.models.User;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {
    @Override
    public List<ExpenseSplit> calculateSplit(List<User> participants, double amount, List<Double> splitValues) {
        double totalAmount = splitValues.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        if (Math.abs(totalAmount - amount) > 0)
            throw new IllegalArgumentException("Total amount and total split value mismatch!");

        List<ExpenseSplit> expenseSplits = new ArrayList<>();

        for (int i = 0; i < participants.size(); i++)
            expenseSplits.add(new ExpenseSplit(participants.get(i).getUserId(), splitValues.get(i)));

        return expenseSplits;
    }
}
