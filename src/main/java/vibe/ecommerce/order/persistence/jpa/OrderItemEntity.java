package vibe.ecommerce.order.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import vibe.ecommerce.product.persistence.jpa.ProductEntity;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
public class OrderItemEntity {

  @Id
  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private OrderEntity order;

  @Id
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;

  @Column(nullable = false)
  private Integer quantity;

  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;

  protected OrderItemEntity() {}

  public OrderItemEntity(
      OrderEntity order, ProductEntity product, Integer quantity, BigDecimal unitPrice) {
    this.order = order;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
}
