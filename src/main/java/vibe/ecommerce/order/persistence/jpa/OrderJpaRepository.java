package vibe.ecommerce.order.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {
  List<OrderEntity> findByCustomer_Id(Integer customerId);
}
