package policyEvaluationengine.model;

public interface PolicyRule<C> {
    RuleResult evaluate(C ctx);
}
