package vibe.ecommerce.product.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record Product(Integer id, String name, BigDecimal price, Instant createdAt) {
  public Product(Integer id, String name, BigDecimal price) {
    this(id, name, price, null);
  }
}
