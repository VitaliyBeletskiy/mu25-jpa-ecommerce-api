package vibe.ecommerce.customer.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(@NotBlank String fullName, @NotBlank String email) {}
