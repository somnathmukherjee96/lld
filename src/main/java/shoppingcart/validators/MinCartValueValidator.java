package shoppingcart.validators;

import shoppingcart.models.Cart;
import shoppingcart.models.Coupon;

public class MinCartValueValidator extends CouponValidator {
    @Override
    public void validate(Coupon coupon, Cart cart) {
        if (cart.getRawSubtotal() < coupon.getMinOrderValue())
            throw new RuntimeException("Minimum order value not met!");
        if (next != null) next.validate(coupon, cart);
    }
}
