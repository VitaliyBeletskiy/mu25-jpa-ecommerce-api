package vibe.ecommerce.order.persistence.inmemory;

import org.springframework.stereotype.Repository;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderRepository;
import vibe.ecommerce.order.domain.OrderStatus;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

  private final Map<Integer, Order> storage = new HashMap<>();
  private int currentId = 0;

  @Override
  public Order save(Order order) {
    currentId++;
    Instant now = Instant.now();
    Order saved = new Order(currentId, order.customerId(), order.status(), now);
    storage.put(currentId, saved);
    return saved;
  }

  @Override
  public Optional<Order> findById(Integer id) {
    return Optional.ofNullable(storage.get(id));
  }

  @Override
  public List<Order> findByCustomerId(Integer customerId) {
    return storage.values().stream()
        .filter(order -> order.customerId().equals(customerId))
        .toList();
  }
}
