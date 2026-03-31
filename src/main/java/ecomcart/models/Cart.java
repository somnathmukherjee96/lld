package ecomcart.models;

import ecomcart.enums.CartStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Cart {
    private final String cartId;
    private final String userId;
    private final List<CartItem> cartItems;
    private Coupon appliedCoupon;
    private Discount cartLevelDiscount;
    private CartStatus cartStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final int MAX_QTY_PER_ITEM = 10;

    public Cart(String cartId, String userId, List<CartItem> cartItems, Coupon appliedCoupon) {
        this.cartId = cartId;
        this.userId = userId;
        this.cartItems = cartItems;
        this.appliedCoupon = appliedCoupon;
        this.createdAt = LocalDateTime.now();
    }


    public void setAppliedCoupon(Coupon appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

    public void setCartLevelDiscount(Discount cartLevelDiscount) {
        this.cartLevelDiscount = cartLevelDiscount;
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

    public Coupon getAppliedCoupon() {
        return appliedCoupon;
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
        cartLevelDiscount = null;
        appliedCoupon = null;
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
