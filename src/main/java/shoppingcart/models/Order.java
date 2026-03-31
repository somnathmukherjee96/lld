package shoppingcart.models;

import java.time.LocalDateTime;
import java.util.List;

public record Order(String orderId, List<OrderItem> orderItems, double totalAmount, LocalDateTime createdAt) {
}
