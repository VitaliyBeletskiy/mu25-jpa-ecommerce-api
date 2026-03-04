package vibe.ecommerce.customer.service;

import vibe.ecommerce.customer.domain.Customer;

public interface CustomerService {
  public Customer createCustomer(String fullName, String email);
}
