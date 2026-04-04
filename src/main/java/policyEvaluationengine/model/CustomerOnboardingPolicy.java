package policyEvaluationengine.model;

import policyEvaluationengine.contexts.CustomerOnboardingContext;

import java.util.List;
import java.util.Map;

public class CustomerOnboardingPolicy implements Policy<CustomerOnboardingContext> {
    private final String policyId;
    private final List<PolicyRule<CustomerOnboardingContext>> policyRules;

    public CustomerOnboardingPolicy(String policyId, List<PolicyRule<CustomerOnboardingContext>> policyRules) {
        this.policyId = policyId;
        this.policyRules = policyRules;
    }

    @Override
    public String getPolicyId() {
        return policyId;
    }

    @Override
    public CustomerOnboardingContext buildContext(Map<String, Object> data) {
        return CustomerOnboardingContext.from(data);
    }

    @Override
    public List<PolicyRule<CustomerOnboardingContext>> getRules() {
        return policyRules;
    }
}
