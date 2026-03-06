package vibe.ecommerce.order.api.dto;

import jakarta.validation.constraints.NotNull;
import vibe.ecommerce.order.domain.PaymentMethod;

import java.math.BigDecimal;

public record PayOrderRequest(@NotNull BigDecimal amount, @NotNull PaymentMethod paymentMethod) {}
