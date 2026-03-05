package vibe.ecommerce.order.api.dto;

import vibe.ecommerce.order.domain.Order;

public class OrderMapper {
  public static OrderResponse toOrderResponse(Order order) {
    return new OrderResponse(
        order.id(), order.customerId(), order.status(), order.createdAt());
  }
}
