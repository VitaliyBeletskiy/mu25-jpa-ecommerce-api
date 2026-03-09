package vibe.ecommerce.order.persistence.jpa;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemId implements Serializable {

  private Integer order;
  private Integer product;

  public OrderItemId() {}

  public OrderItemId(Integer order, Integer product) {
    this.order = order;
    this.product = product;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderItemId that)) return false;
    return Objects.equals(order, that.order) && Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(order, product);
  }
}
