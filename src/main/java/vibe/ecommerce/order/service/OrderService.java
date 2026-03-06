package vibe.ecommerce.order.service;

import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderItem;

import java.util.List;

public interface OrderService {
  Order createOrder(Integer customerId);

  Order getOrder(Integer id);

  List<Order> getOrdersForCustomer(Integer customerId);

  OrderItem addOrderItem(Integer orderId, Integer productId, Integer quantity);

  OrderItem getOrderItem(Integer orderId, Integer productId);
}
