package vibe.ecommerce.order.api.dto;

import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(@NotNull Integer customerId) {}
