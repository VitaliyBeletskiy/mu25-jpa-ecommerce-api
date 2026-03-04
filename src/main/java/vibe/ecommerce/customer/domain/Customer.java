package vibe.ecommerce.customer.domain;

import java.time.LocalDateTime;

public record Customer(Integer id, String fullName, String email, LocalDateTime createdAt) {}
