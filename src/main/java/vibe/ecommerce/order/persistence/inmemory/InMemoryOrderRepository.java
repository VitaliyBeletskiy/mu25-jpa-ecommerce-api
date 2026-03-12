package vibe.ecommerce.order.persistence.inmemory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.domain.OrderRepository;
import vibe.ecommerce.order.domain.OrderStatus;
import vibe.ecommerce.order.domain.Payment;
import vibe.ecommerce.order.domain.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.persistence.mode", havingValue = "inmemory")
public class InMemoryOrderRepository implements OrderRepository {

  private final Map<Integer, Order> orders = new HashMap<>();
  private int currentOrderId = 0;

  private record OrderItemKey(Integer orderId, Integer productId) {}

  private final Map<OrderItemKey, OrderItem> orderItems = new HashMap<>();

  private final Map<Integer, Payment> payments = new HashMap<>();

  @Override
  public Order save(Order order) {
    currentOrderId++;
    Instant now = Instant.now();
    Order saved = new Order(currentOrderId, order.customerId(), order.status(), now);
    orders.put(currentOrderId, saved);
    return saved;
  }

  @Override
  public Optional<Order> findById(Integer id) {
    return Optional.ofNullable(orders.get(id));
  }

  @Override
  public List<Order> findByCustomerId(Integer customerId) {
    return orders.values().stream().filter(order -> order.customerId().equals(customerId)).toList();
  }

  @Override
  public OrderItem addItem(Integer orderId, Integer productId, Integer quantity, BigDecimal unitPrice) {
    OrderItemKey key = new OrderItemKey(orderId, productId);
    OrderItem orderItem = new  OrderItem(orderId, productId, quantity, unitPrice);
    orderItems.put(key, new  OrderItem(orderId, productId, quantity, unitPrice));
    return orderItem;
  }

  @Override
  public List<OrderItem> findItems(Integer orderId) {
    return orderItems.values().stream().filter(oi -> oi.orderId().equals(orderId)).toList();
  }

  @Override
  public Payment savePayment(Integer orderId, BigDecimal amount, PaymentMethod method) {
    Instant now = Instant.now();
    Payment saved = new Payment(orderId, amount, method, now);
    payments.put(orderId, saved);
    Order order = orders.get(orderId);
    Order paidOrder =
        new Order(order.id(), order.customerId(), OrderStatus.PAID, order.createdAt());
    orders.put(order.id(), paidOrder);
    return saved;
  }

  @Override
  public Optional<Payment> findPayment(Integer orderId) {
    return Optional.ofNullable(payments.get(orderId));
  }
}
