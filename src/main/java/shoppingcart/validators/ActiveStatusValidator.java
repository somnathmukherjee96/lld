package shoppingcart.validators;

import shoppingcart.models.Cart;
import shoppingcart.models.Coupon;

public class ActiveStatusValidator extends CouponValidator {
    @Override
    public void validate(Coupon coupon, Cart cart) {
        if (!coupon.isActive())
            throw new RuntimeException("Coupon is not active!");
        if (next != null) next.validate(coupon, cart);
    }
}
