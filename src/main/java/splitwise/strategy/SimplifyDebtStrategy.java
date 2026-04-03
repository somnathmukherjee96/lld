package splitwise.strategy;

import splitwise.models.Settlement;

import java.util.List;
import java.util.Map;

public interface SimplifyDebtStrategy {
    List<Settlement> simplify(Map<String, Double> netBalances);
}
