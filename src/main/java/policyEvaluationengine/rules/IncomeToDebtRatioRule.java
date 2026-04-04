package policyEvaluationengine.rules;

import policyEvaluationengine.contexts.CustomerOnboardingContext;
import policyEvaluationengine.model.PolicyRule;
import policyEvaluationengine.model.RuleResult;

public class IncomeToDebtRatioRule implements PolicyRule<CustomerOnboardingContext> {
    private final double MAX_RATIO = 0.4;

    @Override
    public RuleResult evaluate(CustomerOnboardingContext ctx) {
        return ctx.getIncomeToDebtRatio() >= MAX_RATIO
                ? RuleResult.fail("Income to Debt Ratio exceeds max allowed")
                : RuleResult.pass();
    }
}
