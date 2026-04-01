package shoppingcart.models;

import java.time.LocalDateTime;
import java.util.List;

public record Order(String orderId, String userId, List<OrderItem> orderItems, double totalAmount, String couponCode,double discount,
                    LocalDateTime createdAt) {
}
