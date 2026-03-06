package vibe.ecommerce.order.service;

import org.springframework.stereotype.Service;
import vibe.ecommerce.customer.domain.CustomerRepository;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.domain.OrderRepository;
import vibe.ecommerce.order.domain.OrderStatus;
import vibe.ecommerce.product.domain.Product;
import vibe.ecommerce.product.domain.ProductRepository;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

  private final OrderRepository orderRepo;
  private final CustomerRepository customerRepo;
  private final ProductRepository productRepo;

  public DefaultOrderService(
      OrderRepository orderRepository,
      CustomerRepository customerRepository,
      ProductRepository productRepo) {
    this.orderRepo = orderRepository;
    this.customerRepo = customerRepository;
    this.productRepo = productRepo;
  }

  @Override
  public Order createOrder(Integer customerId) {
    customerRepo
        .findById(customerId)
        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    return orderRepo.save(new Order(null, customerId, OrderStatus.NEW, null));
  }

  @Override
  public Order getOrder(Integer id) {
    return orderRepo
        .findOrderById(id)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
  }

  @Override
  public List<Order> getOrdersForCustomer(Integer customerId) {
    customerRepo
        .findById(customerId)
        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    return orderRepo.findByCustomerId(customerId);
  }

  @Override
  public OrderItem addOrderItem(Integer orderId, Integer productId, Integer quantity) {
    orderRepo
        .findOrderById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    Product product =
        productRepo
            .findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    return orderRepo.saveOrderItem(new OrderItem(orderId, productId, quantity, product.price()));
  }

  @Override
  public OrderItem getOrderItem(Integer orderId, Integer productId) {
    orderRepo
        .findOrderById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    return orderRepo
        .findOrderItem(orderId, productId)
        .orElseThrow(() -> new IllegalArgumentException("OrderItem not found"));
  }
}
