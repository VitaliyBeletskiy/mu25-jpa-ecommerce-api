package vibe.ecommerce.order.persistence.jpa;

import vibe.ecommerce.order.domain.Payment;

public final class PaymentEntityMapper {

  private PaymentEntityMapper() {}

  public static Payment toDomain(PaymentEntity entity) {
    return new Payment(
        entity.getOrder().getId(), entity.getAmount(), entity.getMethod(), entity.getPaidAt());
  }
}
