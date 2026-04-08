package splitwise.services;

import splitwise.models.Expense;
import splitwise.models.ExpenseSplit;
import splitwise.models.Group;
import splitwise.strategy.SimplifyDebtStrategy;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    private final SimplifyDebtStrategy simplifyDebtStrategy;

    public BalanceService(SimplifyDebtStrategy simplifyDebtStrategy) {
        this.simplifyDebtStrategy = simplifyDebtStrategy;
    }

    public void updateBalanceForExpense(Group group, Expense expense){
        String payerId = expense.getPaidBy();

        for(ExpenseSplit split:expense.getSplits()){
            String participantId = split.getUserId();

            if(participantId.equals(payerId)) continue;

            group.updateBalnce(participantId, payerId, split.getShare());
        }
    }

    public Map<String, Double> getNetBalances() {
       return new HashMap<>();
    }
}
