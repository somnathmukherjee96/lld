package splitwise.strategy;

import splitwise.models.Settlement;

import java.time.LocalDateTime;
import java.util.*;

public class GreedySimplifyDebtStrategy implements SimplifyDebtStrategy {
    @Override
    public List<Settlement> simplify(Map<String, Double> netBalances) {
        Deque<Map.Entry<String, Double>> creditors = new ArrayDeque<>();
        Deque<Map.Entry<String, Double>> debtors = new ArrayDeque<>();

        for (Map.Entry<String, Double> entry : netBalances.entrySet()) {
            if (entry.getValue() > 0)
                creditors.add(entry);
            else if (entry.getValue() < 0)
                debtors.add(entry);
        }

        List<Settlement> settlements = new ArrayList<>();

        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            Map.Entry<String, Double> creditor = creditors.poll();
            Map.Entry<String, Double> debtor = debtors.poll();

            double settlementAmount = Math.min(creditor.getValue(), -debtor.getValue());

            settlements.add(new Settlement(UUID.randomUUID().toString(), creditor.getKey(), debtor.getKey(), settlementAmount, LocalDateTime.now()));

            double creditorRemainingAmount = creditor.getValue() - settlementAmount;
            double debtorRemainingAmount = debtor.getValue() + settlementAmount;

            if (creditorRemainingAmount > 0)
                creditors.offer(new AbstractMap.SimpleEntry<>(creditor.getKey(), creditorRemainingAmount));
            if (debtorRemainingAmount < 0)
                debtors.offer(new AbstractMap.SimpleEntry<>(debtor.getKey(), debtorRemainingAmount));
        }

        return settlements;
    }
}
