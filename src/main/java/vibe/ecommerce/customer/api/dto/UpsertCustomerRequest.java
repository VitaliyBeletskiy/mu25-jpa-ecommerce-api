package vibe.ecommerce.customer.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpsertCustomerRequest(@NotBlank String fullName, @NotBlank @Email String email) {}
