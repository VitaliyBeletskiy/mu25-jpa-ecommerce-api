package vibe.ecommerce.order.persistence.jpa;

import vibe.ecommerce.order.domain.OrderItem;

public final class OrderItemEntityMapper {

  private OrderItemEntityMapper() {}

  public static OrderItem toDomain(OrderItemEntity entity) {
    return new OrderItem(
        entity.getOrder().getId(),
        entity.getProduct().getId(),
        entity.getQuantity(),
        entity.getUnitPrice());
  }
}
