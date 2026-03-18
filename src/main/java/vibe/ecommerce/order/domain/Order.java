package vibe.ecommerce.order.domain;

import vibe.ecommerce.common.error.OrderCannotBeCancelledException;

import java.time.Instant;

public record Order(Integer id, Integer customerId, OrderStatus status, Instant createdAt) {
  public Order cancel() {
    if (this.status != OrderStatus.NEW) throw new OrderCannotBeCancelledException(this.id);
    return new Order(this.id, this.customerId, OrderStatus.CANCELED, this.createdAt);
  }
}
