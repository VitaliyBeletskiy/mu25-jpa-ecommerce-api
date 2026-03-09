package vibe.ecommerce.order.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Integer> {
  Optional<PaymentEntity> findByOrder_Id(Integer orderId);
}
