package vibe.ecommerce.customer.domain;

import java.time.Instant;

public record Customer(Integer id, String fullName, String email, Instant createdAt) {
  public Customer(String fullName, String email) {
    this(null, fullName, email, null);
  }

  public Customer(Integer id, String fullName, String email) {
    this(id, fullName, email, null);
  }
}
