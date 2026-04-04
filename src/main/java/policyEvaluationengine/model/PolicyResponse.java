package policyEvaluationengine.model;

import policyEvaluationengine.enums.Decision;

public class PolicyResponse {
    private final String policyId;
    private final Decision decision;

    public PolicyResponse(String policyId, Decision decision) {
        this.policyId = policyId;
        this.decision = decision;
    }

    public String getPolicyId() {
        return policyId;
    }

    public Decision getDecision() {
        return decision;
    }
}
