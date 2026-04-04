package policyEvaluationengine.rules;

import policyEvaluationengine.contexts.CustomerOnboardingContext;
import policyEvaluationengine.model.PolicyRule;
import policyEvaluationengine.model.RuleResult;

public class CreditScoreRule implements PolicyRule<CustomerOnboardingContext> {
    private final int MIN_SCORE = 700;

    @Override
    public RuleResult evaluate(CustomerOnboardingContext ctx) {
        int creditScore = ctx.getCreditScore();
        if (creditScore < MIN_SCORE)
            return RuleResult.fail("Credit score is below minimum allowed credit score");
        return RuleResult.pass();
    }
}
