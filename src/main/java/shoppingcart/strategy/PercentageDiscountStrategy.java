package shoppingcart.strategy;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private final double percentageDiscount;

    public PercentageDiscountStrategy(double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public double apply(double cartTotal) {
        double discount = cartTotal * (percentageDiscount / 100);
        return Math.max(0, cartTotal - discount);
    }
}
