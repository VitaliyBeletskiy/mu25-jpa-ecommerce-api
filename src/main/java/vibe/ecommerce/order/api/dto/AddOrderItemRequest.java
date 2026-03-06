package vibe.ecommerce.order.api.dto;

import jakarta.validation.constraints.NotNull;

public record AddOrderItemRequest(@NotNull Integer productId, @NotNull Integer quantity) {}
