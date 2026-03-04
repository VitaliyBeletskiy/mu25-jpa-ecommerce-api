package vibe.ecommerce.product.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(Integer id, String name, BigDecimal price, LocalDateTime createdAt) {}
