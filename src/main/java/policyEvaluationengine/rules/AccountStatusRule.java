package policyEvaluationengine.rules;

import policyEvaluationengine.contexts.CustomerOnboardingContext;
import policyEvaluationengine.enums.AccountStatus;
import policyEvaluationengine.model.PolicyRule;
import policyEvaluationengine.model.RuleResult;

public class AccountStatusRule implements PolicyRule<CustomerOnboardingContext> {
    @Override
    public RuleResult evaluate(CustomerOnboardingContext ctx) {
        AccountStatus status = ctx.getAccountStatus();
        return status == AccountStatus.ACTIVE
                ? RuleResult.pass()
                : RuleResult.fail("Account status is not Active");
    }
}
