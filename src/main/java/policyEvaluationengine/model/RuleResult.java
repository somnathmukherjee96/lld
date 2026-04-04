package policyEvaluationengine.model;

public class RuleResult {
    private final boolean passed;
    private final String reason;

    public RuleResult(boolean passed, String reason) {
        this.passed = passed;
        this.reason = reason;
    }

    public boolean isPassed() {
        return passed;
    }

    public String getReason() {
        return reason;
    }

    public static RuleResult pass() {
        return new RuleResult(true, null);
    }

    public static RuleResult fail(String reason) {
        if (reason == null || reason.isBlank())
            throw new IllegalArgumentException("Denial reason can't be blank");

        return new RuleResult(false, reason);
    }
}
