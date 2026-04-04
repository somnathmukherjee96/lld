package policyEvaluationengine.model;

import policyEvaluationengine.contexts.CustomerOnboardingContext;

import java.util.List;
import java.util.Map;

public class CustomerOnboardingPolicy implements Policy<CustomerOnboardingContext> {
    @Override
    public String getPolicyId() {
        return "CUSTOMER_ONBOARDING_POLICY";
    }

    @Override
    public CustomerOnboardingContext buildContext(Map<String, Object> data) {
        return CustomerOnboardingContext.from(data);
    }

    @Override
    public List<PolicyRule<CustomerOnboardingContext>> getRules() {
        return List.of(new AccountStatusRule());
    }
}
