package policyEvaluationengine.model;

import java.util.Collections;
import java.util.Map;

public class PolicyRequest {
    private final String policyId;
    private final Map<String, Object> data;

    public PolicyRequest(String policyId, Map<String, Object> data) {
        this.policyId = policyId;
        this.data = Collections.unmodifiableMap(data);
    }

    public String getPolicyId() {
        return policyId;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
