package vibe.ecommerce.order.domain;

import java.util.List;

public record OrderDetails(Order order, List<OrderItem> items, Payment payment) {}
