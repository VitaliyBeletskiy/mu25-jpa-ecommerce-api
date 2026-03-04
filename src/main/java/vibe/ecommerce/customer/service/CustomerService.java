package vibe.ecommerce.customer.service;

import vibe.ecommerce.customer.domain.Customer;

public interface CustomerService {
  Customer createCustomer(String fullName, String email);

  Customer getCustomer(Integer id);
}
