package shoppingcart.strategy;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private final double percentageDiscount;

    public PercentageDiscountStrategy(double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public double calculate(double cartTotal) {
        return cartTotal * (percentageDiscount / 100);
    }
}
