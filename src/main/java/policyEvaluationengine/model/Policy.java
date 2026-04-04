package policyEvaluationengine.model;

import policyEvaluationengine.enums.Decision;

import java.util.List;
import java.util.Map;

public interface Policy<C> {
    String getPolicyId();

    C buildContext(Map<String, Object> data);

    List<PolicyRule<C>> getRules();

    default PolicyResponse evaluate(Map<String, Object> data) {
        C context = buildContext(data);

        for (PolicyRule<C> rule : getRules()) {
            RuleResult result = rule.evaluate(context);
            if (!result.isPassed())
                return new PolicyResponse(getPolicyId(), Decision.DENY);
        }

        return new PolicyResponse(getPolicyId(), Decision.ALLOW);
    }
}
