package vibe.ecommerce.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vibe.ecommerce.common.error.CustomerNotFoundException;
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
    return repo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }

  @Transactional(readOnly = true)
  @Override
  public List<Customer> getCustomers() {
    return repo.findAll();
  }

  @Transactional
  @Override
  public Customer updateCustomer(Integer id, String fullName, String email) {
    repo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    return repo.save(new Customer(id, fullName, email));
  }

  @Transactional
  @Override
  public void deleteCustomer(Integer id) {
    repo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    repo.delete(id);
  }
}
