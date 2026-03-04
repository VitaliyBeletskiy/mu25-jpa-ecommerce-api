package vibe.ecommerce.customer.service;

import org.springframework.stereotype.Service;
import vibe.ecommerce.customer.domain.Customer;
import vibe.ecommerce.customer.domain.CustomerRepository;

@Service
public class DefaultCustomerService implements CustomerService {

  private final CustomerRepository repo;

  public DefaultCustomerService(CustomerRepository customerRepository) {
    this.repo = customerRepository;
  }

  @Override
  public Customer createCustomer(String fullName, String email) {
    return repo.save(new Customer(null, fullName, email, null));
  }

  @Override
  public Customer getCustomer(Integer id) {
    return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
  }
}
