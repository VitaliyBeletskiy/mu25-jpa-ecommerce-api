package vibe.ecommerce.customer.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {}
