package shoppingcart.models;

import shoppingcart.enums.CartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private final String cartId;
    private final String userId;
    private final List<CartItem> cartItems;
    private CartStatus cartStatus;
    private Coupon appliedCoupon;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final int MAX_QTY_PER_ITEM = 10;

    public Cart(String cartId, String userId) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartItems = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        cartStatus = CartStatus.ACTIVE;
    }

    public Coupon getAppliedCoupon() {
        return appliedCoupon;
    }

    public void setAppliedCoupon(Coupon appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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


    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }

    public void addItem(Product product, int qty) {
        Optional<CartItem> existingItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQty = qty + item.getQuantity();
            if (newQty > MAX_QTY_PER_ITEM)
                throw new IllegalArgumentException("Max quantity exceeded");
            item.setQuantity(qty);
        } else {
            if (qty > MAX_QTY_PER_ITEM)
                throw new IllegalArgumentException("Max quantity exceeded");
            cartItems.add(new CartItem(product, qty));
        }
    }

    public void removeItem(CartItem item) {
        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(item.getProduct()));
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void updateQuantity(CartItem item, int qty) {
        cartItems.stream()
                .filter(cartItem -> cartItem.equals(item))
                .findFirst()
                .ifPresentOrElse(cartItem -> cartItem.setQuantity(qty),
                        () -> {
                            throw new IllegalArgumentException("Cart Item not found");
                        });
    }

    public double getRawSubtotal() {
        return cartItems.stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
    }


}
