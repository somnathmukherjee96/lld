package shoppingcart.models;

public class PriceSummary {
    private final double subTotal;
    private final double cartLevelDiscount;
    private final double itemLevelDiscount;
    private final double couponDiscount;
    private final double taxAmount;
    private final double finalTotal;

    public PriceSummary(double subTotal, double cartLevelDiscount, double itemLevelDiscount, double couponDiscount, double taxAmount, double finalTotal) {
        this.subTotal = subTotal;
        this.cartLevelDiscount = cartLevelDiscount;
        this.itemLevelDiscount = itemLevelDiscount;
        this.couponDiscount = couponDiscount;
        this.taxAmount = taxAmount;
        this.finalTotal = finalTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getCartLevelDiscount() {
        return cartLevelDiscount;
    }

    public double getItemLevelDiscount() {
        return itemLevelDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getFinalTotal() {
        return finalTotal;
    }
}
