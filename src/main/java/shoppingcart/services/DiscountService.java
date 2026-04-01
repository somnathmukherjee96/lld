package shoppingcart.services;

import shoppingcart.models.Cart;
import shoppingcart.models.Coupon;
import shoppingcart.models.Discount;
import shoppingcart.validators.ActiveStatusValidator;
import shoppingcart.validators.CouponValidator;
import shoppingcart.validators.ExpiryValidator;
import shoppingcart.validators.MinCartValueValidator;

import java.util.Map;

public class DiscountService {
    private final Map<String, Discount> discountRegistry;
    private final Map<String, Coupon> couponRegistry;

    public DiscountService(Map<String, Discount> discountRegistry, Map<String, Coupon> couponRegistry) {
        this.discountRegistry = discountRegistry;
        this.couponRegistry = couponRegistry;
    }

    public void addCoupon(Coupon coupon) {
        couponRegistry.put(coupon.getCode(), coupon);
        String discountId = coupon.getDiscount().discountId();
        discountRegistry.put(discountId, coupon.getDiscount());
    }

    public void addDiscount(Discount discount) {
        discountRegistry.put(discount.discountId(), discount);
    }

    public double applyDiscount(double rawSubTotal, String discountId) {
        Discount discount = discountRegistry.entrySet().stream()
                .filter(entry -> entry.getKey().equals(discountId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Discount not found!"));

        if (discount.isExpired())
            throw new IllegalArgumentException("Discount " + discount.discountId() + " is expired!");

        return discount.discountType().calculate(rawSubTotal);

    }

    public double applyCoupon(Cart cart, String couponId) {
        CouponValidator couponValidator = new ExpiryValidator();
        couponValidator.setNext(new MinCartValueValidator())
                .setNext(new ActiveStatusValidator());

        Coupon coupon = couponRegistry.get(couponId);

        if (coupon == null)
            throw new IllegalArgumentException("Coupon not found: " + couponId);

        couponValidator.validate(coupon, cart);

        cart.setAppliedCoupon(coupon);

        return applyDiscount(cart.getRawSubtotal(), coupon.getDiscount().discountId());
    }
}
