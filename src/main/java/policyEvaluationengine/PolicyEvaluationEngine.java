package policyEvaluationengine;

import policyEvaluationengine.model.Policy;
import policyEvaluationengine.model.PolicyRequest;
import policyEvaluationengine.model.PolicyResponse;
import policyEvaluationengine.repo.PolicyRegistry;

public class PolicyEvaluationEngine {
    private final PolicyRegistry policyRegistry;

    public PolicyEvaluationEngine(PolicyRegistry policyRegistry) {
        this.policyRegistry = policyRegistry;
    }

    public PolicyResponse evaluatePolicy(PolicyRequest policyRequest) {
        Policy<?> policy = policyRegistry.resolve(policyRequest.getPolicyId());

        return policy.evaluate(policyRequest.getData());
    }
}
