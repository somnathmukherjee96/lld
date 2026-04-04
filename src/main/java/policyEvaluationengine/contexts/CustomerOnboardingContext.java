package policyEvaluationengine.contexts;

import policyEvaluationengine.enums.AccountStatus;

import java.util.Map;

public class CustomerOnboardingContext {
    private final AccountStatus accountStatus;
    private final double incomeToDebtRatio;
    private final int creditScore;

    public CustomerOnboardingContext(AccountStatus accountStatus, double incomeToDebtRatio, int creditScore) {
        this.accountStatus = accountStatus;
        this.incomeToDebtRatio = incomeToDebtRatio;
        this.creditScore = creditScore;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public double getIncomeToDebtRatio() {
        return incomeToDebtRatio;
    }

    public int getCreditScore() {
        return creditScore;
    }

    @SuppressWarnings("unchecked")
    public static CustomerOnboardingContext from(Map<String, Object> data) {
        Map<String, Object> account = (Map<String, Object>) data.get("account");
        Map<String, Object> customer = (Map<String, Object>) data.get("customer");

        if (account == null)
            throw new IllegalArgumentException("Account data not present");
        if (customer == null)
            throw new IllegalArgumentException("Customer Data is not present");

        return new CustomerOnboardingContext((AccountStatus) account.get("status"),
                (double) customer.get("incomeToDebtRatio"),
                (int) customer.get("creditScore"));
    }
}
