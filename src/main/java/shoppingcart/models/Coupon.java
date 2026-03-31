package shoppingcart.models;

public class Coupon {
    private final String code;
    private final Discount discount;
    private final int usageLimit;
    private int usedCount;

    public Coupon(String code, Discount discount, int usageLimit) {
        this.code = code;
        this.discount = discount;
        this.usageLimit = usageLimit;
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
