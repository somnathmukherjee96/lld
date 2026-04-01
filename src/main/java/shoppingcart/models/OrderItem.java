package shoppingcart.models;

public record OrderItem(String productId, String productName, double priceAtCheckout, int qty) {
}
