package shoppingcart.models;

import shoppingcart.enums.CartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
        this.updatedAt = LocalDateTime.now();
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
        return Collections.unmodifiableList(cartItems);
    }


    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public void addItem(Product product, int qty) {
        if (qty <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        Optional<CartItem> existingItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getProductId().equals(product.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQty = qty + item.getQuantity();
            if (newQty > MAX_QTY_PER_ITEM)
                throw new IllegalArgumentException("Max quantity exceeded");
            item.setQuantity(newQty);
        } else {
            if (qty > MAX_QTY_PER_ITEM)
                throw new IllegalArgumentException("Max quantity exceeded");
            cartItems.add(new CartItem(product, qty));
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void removeItem(CartItem item) {
        boolean removed = cartItems.removeIf(cartItem -> cartItem.getProduct().equals(item.getProduct()));

        if (!removed)
            throw new IllegalArgumentException("Product " + item + " is not in the cart");
        this.updatedAt = LocalDateTime.now();
    }

    public void clearCart() {
        cartItems.clear();
        this.appliedCoupon = null;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateQuantity(CartItem item, int qty) {
        if (qty < 1 || qty > MAX_QTY_PER_ITEM)
            throw new IllegalArgumentException("Quantity must be between 1 and " + MAX_QTY_PER_ITEM);

        cartItems.stream()
                .filter(cartItem -> cartItem.equals(item))
                .findFirst()
                .ifPresentOrElse(cartItem -> cartItem.setQuantity(qty),
                        () -> {
                            throw new IllegalArgumentException("Cart Item not found");
                        });
        this.updatedAt = LocalDateTime.now();
    }

    public double getRawSubtotal() {
        return cartItems.stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
    }
}
