package shoppingcart.services;

import shoppingcart.enums.CartStatus;
import shoppingcart.models.Cart;
import shoppingcart.models.Order;
import shoppingcart.models.OrderItem;
import shoppingcart.models.PriceSummary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CheckoutService {

    private final PricingService pricingService;

    public CheckoutService(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public Order checkout(Cart cart) {
        PriceSummary priceSummary = pricingService.calculate(cart);

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> new OrderItem(cartItem.getProduct().getProductId(),
                        cartItem.getProduct().getProductName(),
                        cartItem.getProduct().getBasePrice(),
                        cartItem.getQuantity()))
                .toList();

        Order order = new Order(
                UUID.randomUUID().toString(),
                cart.getUserId(),
                orderItems,
                priceSummary.getFinalTotal(),
                cart.getAppliedCoupon().getCode(),
                priceSummary.getDiscount(),
                LocalDateTime.now()
        );
        cart.setCartStatus(CartStatus.CHECKED_OUT);
        printOrderSummary(order, priceSummary);

        return order;
    }

    private void printOrderSummary(Order order, PriceSummary summary) {
        System.out.println("\n===== Order Confirmed =====");
        System.out.println("Order ID : " + order.orderId());
        System.out.println("User     : " + order.userId());
        System.out.println("\nItems:");
        order.orderItems().forEach(item ->
                System.out.printf("  %-35s x%d  ₹%.2f%n",
                        item.productName(), item.qty(), item.priceAtCheckout()));
        System.out.println();
        summary.print();
        System.out.println("===========================");
    }
}
