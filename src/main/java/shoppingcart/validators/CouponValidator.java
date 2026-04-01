package shoppingcart.validators;

import shoppingcart.models.Cart;
import shoppingcart.models.Coupon;

public abstract class CouponValidator {
    protected CouponValidator next;

    public CouponValidator setNext(CouponValidator next) {
        this.next = next;
        return next;
    }

    public abstract void validate(Coupon coupon, Cart cart);
}
