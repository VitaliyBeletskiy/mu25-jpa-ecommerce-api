package vibe.ecommerce.product.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateProductRequest(@NotBlank String name, BigDecimal price) {}
