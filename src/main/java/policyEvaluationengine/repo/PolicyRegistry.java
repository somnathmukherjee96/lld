package policyEvaluationengine.repo;

import policyEvaluationengine.model.Policy;

import java.util.HashMap;
import java.util.Map;

public class PolicyRegistry {
    private final Map<String, Policy<?>> registry = new HashMap<>();

    public void addPolicy(String policyId, Policy<?> policy) {
        registry.put(policyId, policy);
    }

    public Policy<?> resolve(String policyId) {
        return registry.get(policyId);
    }
}
