package vibe.ecommerce.order.api.dto;

import vibe.ecommerce.order.domain.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderDetailsResponse(
    Integer id,
    Integer customerId,
    OrderStatus status,
    Instant createdAt,
    List<OrderItemResponse> items,
    PaymentResponse payment) {}
