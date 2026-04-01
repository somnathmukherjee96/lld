package shoppingcart.models;

public class Coupon {
    private final String code;
    private final Discount discount;
    private final int usageLimit;
    private int usedCount;
    private boolean isActive;
    private double minOrderValue;

    public Coupon(String code, Discount discount, int usageLimit, double minOrderValue) {
        this.code = code;
        this.discount = discount;
        this.usageLimit = usageLimit;
        this.isActive = true;
        this.minOrderValue = minOrderValue;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(double minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isValid() {
        return !discount.isExpired() && (usageLimit == 0 || usedCount < usageLimit);
    }

    public void incrementUsage() {
        usedCount++;
    }

    public String getCode() {
        return code;
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public int getUsedCount() {
        return usedCount;
    }
}
