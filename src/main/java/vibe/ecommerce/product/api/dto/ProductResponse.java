package vibe.ecommerce.product.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(Integer id, String name, BigDecimal price, Instant createdAt) {}
