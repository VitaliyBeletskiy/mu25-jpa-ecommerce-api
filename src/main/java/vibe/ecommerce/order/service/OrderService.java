package vibe.ecommerce.order.service;

import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderDetails;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.domain.Payment;
import vibe.ecommerce.order.domain.PaymentMethod;

import java.util.List;

public interface OrderService {

  Order createOrder(Integer customerId);

  OrderDetails getOrderDetails(Integer id);

  List<Order> getOrdersForCustomer(Integer customerId);

  OrderItem addOrderItem(AddOrderItemCommand cmd);

  List<OrderItem> getOrderItems(Integer orderId);

  Order cancelOrder(Integer orderId);

  Payment payOrder(Integer orderId, PaymentMethod paymentMethod);
}
