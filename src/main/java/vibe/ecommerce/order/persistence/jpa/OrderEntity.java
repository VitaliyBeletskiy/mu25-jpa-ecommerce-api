package vibe.ecommerce.order.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import vibe.ecommerce.customer.persistence.jpa.CustomerEntity;
import vibe.ecommerce.order.domain.OrderStatus;

import java.time.Instant;

@Getter
@Entity
@Table(name = "orders")
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private CustomerEntity customer;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatus status;

  @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
  private Instant createdAt;

  protected OrderEntity() {}

  public OrderEntity(CustomerEntity customer, OrderStatus status) {
    this.customer = customer;
    this.status = status;
  }

  public void markPaid() {
    this.status = OrderStatus.PAID;
  }
}
