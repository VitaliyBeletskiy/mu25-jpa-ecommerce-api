package vibe.ecommerce.order.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
  List<OrderItemEntity> findByOrder_Id(Integer orderId);
}
