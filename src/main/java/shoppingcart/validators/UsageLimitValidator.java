package shoppingcart.validators;

import shoppingcart.models.Cart;
import shoppingcart.models.Coupon;

public class UsageLimitValidator extends CouponValidator {
    @Override
    public void validate(Coupon coupon, Cart cart) {
        if (coupon.getUsedCount() >= coupon.getUsageLimit())
            throw new RuntimeException("Coupon usage limit reached.");
        if (next != null) next.validate(coupon, cart);
    }
}
