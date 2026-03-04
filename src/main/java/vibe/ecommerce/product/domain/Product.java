package vibe.ecommerce.product.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Product(Integer id, String name, BigDecimal price, LocalDateTime createdAt) {}
