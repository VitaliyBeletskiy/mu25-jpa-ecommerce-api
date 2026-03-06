package vibe.ecommerce.order.api.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
    Integer orderId, Integer productId, Integer quantity, BigDecimal unitPrice) {}
