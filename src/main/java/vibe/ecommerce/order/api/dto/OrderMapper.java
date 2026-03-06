package vibe.ecommerce.order.api.dto;

import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderItem;

public class OrderMapper {
  public static OrderResponse toOrderResponse(Order order) {
    return new OrderResponse(order.id(), order.customerId(), order.status(), order.createdAt());
  }

  public static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
    return new OrderItemResponse(
        orderItem.orderId(), orderItem.productId(), orderItem.quantity(), orderItem.unitPrice());
  }
}
