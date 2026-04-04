package splitwise.services;

import splitwise.strategy.SimplifyDebtStrategy;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    private final SimplifyDebtStrategy simplifyDebtStrategy;

    public BalanceService(SimplifyDebtStrategy simplifyDebtStrategy) {
        this.simplifyDebtStrategy = simplifyDebtStrategy;
    }

    public Map<String, Double> getNetBalances() {
       return new HashMap<>();
    }
}
