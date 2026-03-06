package vibe.ecommerce.order.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record Payment(Integer orderId, BigDecimal amount, PaymentMethod method, Instant paidAt) {}
