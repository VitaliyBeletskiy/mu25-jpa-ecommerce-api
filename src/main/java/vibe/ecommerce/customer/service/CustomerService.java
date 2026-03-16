package vibe.ecommerce.customer.service;

import vibe.ecommerce.customer.domain.Customer;

import java.util.List;

public interface CustomerService {
  Customer createCustomer(String fullName, String email);

  Customer getCustomer(Integer id);

  List<Customer> getCustomers();

  Customer updateCustomer(Integer id, String fullName, String email);
}
