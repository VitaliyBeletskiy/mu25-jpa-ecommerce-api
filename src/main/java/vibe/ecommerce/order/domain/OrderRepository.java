package vibe.ecommerce.order.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

  Order save(Order order);

  Optional<Order> findById(Integer id);

  List<Order> findByCustomerId(Integer customerId);

  OrderItem addItem(Integer orderId, Integer productId, Integer quantity, BigDecimal unitPrice);

  List<OrderItem> findItems(Integer orderId);

  Payment savePayment(Integer orderId, BigDecimal amount, PaymentMethod method);

  Optional<Payment> findPayment(Integer orderId);
}
