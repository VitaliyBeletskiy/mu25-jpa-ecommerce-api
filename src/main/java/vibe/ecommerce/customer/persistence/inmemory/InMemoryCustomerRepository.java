package vibe.ecommerce.customer.persistence.inmemory;

import org.springframework.stereotype.Repository;
import vibe.ecommerce.customer.domain.Customer;
import vibe.ecommerce.customer.domain.CustomerRepository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// @Repository
public class InMemoryCustomerRepository implements CustomerRepository {

  private final Map<Integer, Customer> storage = new HashMap<>();
  private int currentId = 0;

  @Override
  public Customer save(final Customer customer) {
    currentId++;
    Instant now = Instant.now();
    Customer saved = new Customer(currentId, customer.fullName(), customer.email(), now);
    storage.put(currentId, saved);
    return saved;
  }

  @Override
  public Optional<Customer> findById(Integer id) {
    return Optional.ofNullable(storage.get(id));
  }
}
