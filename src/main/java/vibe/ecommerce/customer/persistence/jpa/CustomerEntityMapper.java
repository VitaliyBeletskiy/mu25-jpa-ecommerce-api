package vibe.ecommerce.customer.persistence.jpa;

import vibe.ecommerce.customer.domain.Customer;

public class CustomerEntityMapper {

  private CustomerEntityMapper() {}

  public static CustomerEntity toEntity(Customer customer) {
    return new CustomerEntity(customer.fullName(), customer.email());
  }

  public static Customer toDomain(CustomerEntity customerEntity) {
    return new Customer(
        customerEntity.getId(),
        customerEntity.getFullName(),
        customerEntity.getEmail(),
        customerEntity.getCreatedAt());
  }
}
