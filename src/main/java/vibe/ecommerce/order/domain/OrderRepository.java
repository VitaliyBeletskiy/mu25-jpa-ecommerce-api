package vibe.ecommerce.order.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

  Order save(Order order);

  Optional<Order> findById(Integer id);

  List<Order> findByCustomerId(Integer customerId);
}
