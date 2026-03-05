package vibe.ecommerce.customer.domain;

import java.time.Instant;

public record Customer(Integer id, String fullName, String email, Instant createdAt) {}
