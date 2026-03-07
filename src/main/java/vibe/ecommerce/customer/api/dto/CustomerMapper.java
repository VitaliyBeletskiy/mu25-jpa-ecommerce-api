package vibe.ecommerce.customer.api.dto;

import vibe.ecommerce.customer.domain.Customer;

public class CustomerMapper {

  private CustomerMapper() {}

  public static CustomerResponse toResponse(Customer customer) {
    return new CustomerResponse(
        customer.id(), customer.fullName(), customer.email(), customer.createdAt());
  }
}
