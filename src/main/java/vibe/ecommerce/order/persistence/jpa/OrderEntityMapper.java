package vibe.ecommerce.order.persistence.jpa;

import vibe.ecommerce.order.domain.Order;

public final class OrderEntityMapper {

  private OrderEntityMapper() {}

  public static Order toDomain(OrderEntity entity) {
    return new Order(
        entity.getId(), entity.getCustomer().getId(), entity.getStatus(), entity.getCreatedAt());
  }
}
