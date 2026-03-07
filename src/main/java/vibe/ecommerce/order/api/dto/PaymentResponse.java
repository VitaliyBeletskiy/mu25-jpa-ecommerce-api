package vibe.ecommerce.order.api.dto;

import vibe.ecommerce.order.domain.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponse(
    Integer orderId, BigDecimal amount, PaymentMethod method, Instant paidAt) {}
