package vibe.ecommerce.order.api.dto;

import vibe.ecommerce.order.domain.OrderStatus;

import java.time.Instant;

public record OrderResponse(
    Integer id, Integer customerId, OrderStatus status, Instant createdAt) {}
