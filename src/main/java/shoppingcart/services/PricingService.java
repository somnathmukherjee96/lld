package shoppingcart.services;

import shoppingcart.models.Cart;
import shoppingcart.models.Coupon;
import shoppingcart.models.PriceSummary;

public class PricingService {

    private final DiscountService discountService;

    public PricingService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public PriceSummary calculate(Cart cart) {
        double subTotal = cart.getRawSubtotal();
        double finalTotal = subTotal;
        String couponCode = null;
        double discount = 0;
        Coupon appliedCoupon = cart.getAppliedCoupon();
        if (appliedCoupon != null) {
            couponCode = appliedCoupon.getCode();
            discount = discountService.applyCoupon(cart, cart.getAppliedCoupon().getCode());
            finalTotal = subTotal - discount;
        }

        return new PriceSummary(subTotal, discount, finalTotal, couponCode);
    }
}
