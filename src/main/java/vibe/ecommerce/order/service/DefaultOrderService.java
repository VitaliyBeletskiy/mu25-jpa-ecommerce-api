package vibe.ecommerce.order.service;

import org.springframework.stereotype.Service;
import vibe.ecommerce.customer.domain.CustomerRepository;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderRepository;
import vibe.ecommerce.order.domain.OrderStatus;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

  private final OrderRepository orderRepo;
  private final CustomerRepository customerRepo;

  public DefaultOrderService(
      OrderRepository orderRepository, CustomerRepository customerRepository) {
    this.orderRepo = orderRepository;
    this.customerRepo = customerRepository;
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
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
  }

  @Override
  public List<Order> getOrdersForCustomer(Integer customerId) {
    customerRepo
        .findById(customerId)
        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    return orderRepo.findByCustomerId(customerId);
  }
}
