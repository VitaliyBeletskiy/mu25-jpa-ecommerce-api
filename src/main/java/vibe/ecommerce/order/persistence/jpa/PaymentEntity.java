package vibe.ecommerce.order.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import vibe.ecommerce.order.domain.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Entity
@Table(name = "payment")
public class PaymentEntity {

  @Id
  @Column(name = "order_id")
  private Integer orderId;

  @OneToOne
  @MapsId
  @JoinColumn(name = "order_id", nullable = false)
  private OrderEntity order;

  @Column(nullable = false)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentMethod method;

  @Column(name = "paid_at", nullable = false, insertable = false, updatable = false)
  private Instant paidAt;

  protected PaymentEntity() {}

  public PaymentEntity(OrderEntity order, BigDecimal amount, PaymentMethod method) {
    this.order = order;
    this.amount = amount;
    this.method = method;
  }
}
