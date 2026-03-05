package vibe.ecommerce.customer.api.dto;

import java.time.Instant;

public record CustomerResponse(
    Integer id, String fullName, String email, Instant createdAt) {}
