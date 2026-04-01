package shoppingcart.strategy;

public class FlatDiscountStrategy implements DiscountStrategy {
    private final double flatDiscount;

    public FlatDiscountStrategy(double flatDiscount) {
        this.flatDiscount = flatDiscount;
    }

    @Override
    public double calculate(double cartTotal) {
        return Math.max(0, cartTotal - flatDiscount);
    }
}
