package shoppingcart.models;

public class PriceSummary {
    private final double subTotal;
    private final double discount;
    private final double finalTotal;
    private final String couponApplied;

    public PriceSummary(double subTotal, double discount, double finalTotal, String couponApplied) {
        this.subTotal = subTotal;
        this.discount = discount;
        this.finalTotal = finalTotal;
        this.couponApplied = couponApplied;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void print() {
        System.out.println("  Subtotal        : ₹" + String.format("%.2f", subTotal));
        if (couponApplied != null)
            System.out.println("  Coupon (" + couponApplied + "): -₹" + String.format("%.2f", discount));
        System.out.println("  Final Total     : ₹" + String.format("%.2f", finalTotal));
    }
}
