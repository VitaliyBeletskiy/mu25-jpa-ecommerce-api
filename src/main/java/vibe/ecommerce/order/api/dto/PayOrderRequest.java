package vibe.ecommerce.order.api.dto;

import jakarta.validation.constraints.NotNull;
import vibe.ecommerce.order.domain.PaymentMethod;

public record PayOrderRequest(@NotNull PaymentMethod paymentMethod) {}
