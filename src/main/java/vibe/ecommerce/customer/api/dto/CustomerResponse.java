package vibe.ecommerce.customer.api.dto;

import java.time.LocalDateTime;

public record CustomerResponse(
    Integer id, String fullName, String email, LocalDateTime createdAt) {}
