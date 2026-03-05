package vibe.ecommerce.order.domain;

import java.time.Instant;

public record Order(Integer id, Integer customerId, OrderStatus status, Instant createdAt) {}
