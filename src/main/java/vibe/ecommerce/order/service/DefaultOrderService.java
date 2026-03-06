package vibe.ecommerce.order.service;

import org.springframework.stereotype.Service;
import vibe.ecommerce.customer.domain.CustomerRepository;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.domain.OrderRepository;
import vibe.ecommerce.order.domain.OrderStatus;
import vibe.ecommerce.order.domain.Payment;
import vibe.ecommerce.order.domain.PaymentMethod;
import vibe.ecommerce.product.domain.Product;
import vibe.ecommerce.product.domain.ProductRepository;

import java.math.BigDecimal;
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
  public List<OrderItem> getOrderItems(Integer orderId) {
    orderRepo
        .findOrderById(orderId)
        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    return orderRepo.findOrderItems(orderId);
  }

  @Override
  public Order payOrder(Integer orderId, BigDecimal amount, PaymentMethod paymentMethod) {
    Order order =
        orderRepo
            .findOrderById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    if (order.status() != OrderStatus.NEW) {
      throw new IllegalStateException("Only NEW orders can be paid");
    }
    List<OrderItem> items = orderRepo.findOrderItems(orderId);
    BigDecimal total =
        items.stream()
            .map(oi -> oi.unitPrice().multiply(BigDecimal.valueOf(oi.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    if (amount.compareTo(total) != 0) {
      throw new IllegalArgumentException("Wrong payment amount");
    }
    return orderRepo.savePayment(new Payment(orderId, amount, paymentMethod, null));
  }
}
