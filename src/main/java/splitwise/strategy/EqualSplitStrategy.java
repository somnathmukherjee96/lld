package splitwise.strategy;

import splitwise.models.ExpenseSplit;
import splitwise.models.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public List<ExpenseSplit> calculateSplit(List<User> participants, double amount, List<Double> splitValues) {
        double totalAmount = splitValues.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        if (Math.abs(totalAmount - amount) > 0)
            throw new IllegalArgumentException("Total amount and total split value mismatch!");
        double sharePerUser = amount / participants.size();

        List<ExpenseSplit> expenseSplits = new ArrayList<>();

        for (User participant : participants)
            expenseSplits.add(new ExpenseSplit(participant.getUserId(), sharePerUser));

        return expenseSplits;
    }
}
