package splitwise.strategy;

import splitwise.models.Settlement;

import java.util.List;
import java.util.Map;

public class GreedySimplifyDebtStrategy implements SimplifyDebtStrategy{
    @Override
    public List<Settlement> simplify(Map<String, Double> netBalances) {
        return List.of();
    }
}
