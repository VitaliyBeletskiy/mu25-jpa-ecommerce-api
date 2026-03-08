package vibe.ecommerce.customer.persistence.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import vibe.ecommerce.customer.domain.Customer;
import vibe.ecommerce.customer.domain.CustomerRepository;

import java.util.Optional;

@Repository
public class CustomerRepositoryJpaAdapter implements CustomerRepository {

  private final CustomerJpaRepository jpaRepo;

  @PersistenceContext private EntityManager entityManager;

  public CustomerRepositoryJpaAdapter(CustomerJpaRepository customerJpaRepository) {
    this.jpaRepo = customerJpaRepository;
  }

  @Override
  public Customer save(Customer customer) {
    CustomerEntity entity = CustomerEntityMapper.toEntity(customer);
    CustomerEntity saved = jpaRepo.save(entity);
    entityManager.refresh(saved);
    return CustomerEntityMapper.toDomain(saved);
  }

  @Override
  public Optional<Customer> findById(Integer id) {
    return jpaRepo.findById(id).map(CustomerEntityMapper::toDomain);
  }
}
