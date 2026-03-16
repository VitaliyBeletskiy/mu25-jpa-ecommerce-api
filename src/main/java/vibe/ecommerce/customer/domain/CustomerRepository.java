package vibe.ecommerce.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

  Customer save(Customer customer);

  Optional<Customer> findById(Integer id);

  List<Customer> findAll();

  void delete(Integer id);
}
