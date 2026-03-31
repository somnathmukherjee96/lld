package shoppingcart.services;

import shoppingcart.models.Order;

public class CheckoutService {

    public void checkout(Order order) {
        System.out.println("Order Placed!");

        System.out.println("-----Order Summary-----");
        order.orderItems()
                .forEach(item -> System.out.println(item.productId() + " - " + item.priceAtCheckout()));
    }
}
