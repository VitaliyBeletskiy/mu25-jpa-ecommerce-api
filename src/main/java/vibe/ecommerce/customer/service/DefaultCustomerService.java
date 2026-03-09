package vibe.ecommerce.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vibe.ecommerce.customer.domain.Customer;
import vibe.ecommerce.customer.domain.CustomerRepository;

import java.util.List;

@Service
public class DefaultCustomerService implements CustomerService {

  private final CustomerRepository repo;

  public DefaultCustomerService(CustomerRepository customerRepository) {
    this.repo = customerRepository;
  }

  @Transactional
  @Override
  public Customer createCustomer(String fullName, String email) {
    return repo.save(new Customer(fullName, email));
  }

  @Transactional(readOnly = true)
  @Override
  public Customer getCustomer(Integer id) {
    return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
  }

  @Transactional(readOnly = true)
  @Override
  public List<Customer> getCustomers() {
    return repo.findAll();
  }
}
