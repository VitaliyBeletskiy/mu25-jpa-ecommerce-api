package vibe.ecommerce.order.service;

import vibe.ecommerce.order.domain.Order;

import java.util.List;

public interface OrderService {
  Order createOrder(Integer customerId);

  Order getOrder(Integer id);

  List<Order> getOrdersForCustomer(Integer customerId);
}
