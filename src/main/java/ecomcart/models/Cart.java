package ecomcart.models;

import ecomcart.enums.CartStatus;

import java.util.List;
import java.util.UUID;

public class Cart {
    private final String cartId;
    private final String userId;
    private final List<CartItem> cartItems;
    private final Coupon coupon;
    private CartStatus cartStatus;

    public Cart(String userId, List<CartItem> cartItems, Coupon coupon) {
        this.cartId = UUID.randomUUID().toString();
        this.userId = userId;
        this.cartItems = cartItems;
        this.coupon = coupon;
    }

    public String getCartId() {
        return cartId;
    }

    public String getUserId() {
        return userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }

    public void updateQuantity(CartItem item, int qty) {
        for (CartItem cartItem : cartItems)
            if (item == cartItem)
                cartItem.setQuantity(qty);
    }
}
