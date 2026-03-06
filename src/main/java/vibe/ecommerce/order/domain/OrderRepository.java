package vibe.ecommerce.order.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

  Order save(Order order);

  Optional<Order> findOrderById(Integer id);

  List<Order> findByCustomerId(Integer customerId);

  OrderItem saveOrderItem(OrderItem orderItem);

  List<OrderItem> findOrderItems(Integer orderId);

  Order savePayment(Payment payment);

//  Optional<Payment> findPaymentByOrderId(Integer orderId);
}
