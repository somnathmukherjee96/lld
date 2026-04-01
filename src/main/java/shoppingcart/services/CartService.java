package shoppingcart.services;

import shoppingcart.models.Cart;
import shoppingcart.models.CartItem;
import shoppingcart.models.Product;
import shoppingcart.observers.InventoryObserver;

import java.util.HashMap;
import java.util.Map;

public class CartService implements InventoryObserver {

    private final Map<String, Cart> cartRegistry;
    private final InventoryService inventoryService;
    private final ProductService productService;

    public CartService(InventoryService inventoryService, ProductService productService) {
        this.cartRegistry = new HashMap<>();
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    public void addCartToTheRegistry(Cart cart) {
        cartRegistry.put(cart.getCartId(), cart);
    }

    public Cart getCart(String cartId) {
        return cartRegistry.entrySet().stream()
                .filter(entry -> entry.getKey().equals(cartId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cart " + cartId + " not found!"));
    }

    public void addItem(String cartId, String productId, int qty) {
        Cart cart = getCart(cartId);
        Product product = productService.getProduct(productId);
        if (inventoryService.hasStock(productId, qty)) {
            cart.addItem(product, qty);
            inventoryService.reserveStock(productId, qty);
        } else {
            throw new RuntimeException("Product out of stock!");
        }
    }

    public void removeItem(String cartId, CartItem cartItem) {
        Cart cart = getCart(cartId);

        cart.removeItem(cartItem);
        inventoryService.releaseStock(cartItem.getProduct().getProductId(), cartItem.getQuantity());
    }

    public void updateQuantity(String cartId, CartItem cartItem, int qty) {
        Cart cart = getCart(cartId);
        String productId = cartItem.getProduct().getProductId();

        if (inventoryService.hasStock(productId, qty))
            cart.updateQuantity(cartItem, qty);
        else
            throw new RuntimeException("Product out of stock!");
    }

    public void clearCart(String cartId) {
        Cart cart = getCart(cartId);
        cart.clearCart();
        cartRegistry.remove(cartId);
    }

    public double getRawSubtotal(String cartId) {
        Cart cart = getCart(cartId);
        return cart.getRawSubtotal();
    }

    @Override
    public void update(Map<String, Integer> inventory) {
        inventory.forEach((key, value) -> System.out.println(key + "->" + value));
    }
}
