package vibe.ecommerce.product.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Entity
@Table(name = "product")
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true, length = 255)
  private String name;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
  private Instant createdAt;

  protected ProductEntity() {}

  public ProductEntity(Integer id, String name, BigDecimal price, Instant createdAt) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.createdAt = createdAt;
  }
}
