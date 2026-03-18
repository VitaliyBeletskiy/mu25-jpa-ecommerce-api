package vibe.ecommerce.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vibe.ecommerce.common.error.CustomerNotFoundException;
import vibe.ecommerce.common.error.OrderCannotBePaidException;
import vibe.ecommerce.common.error.OrderHasNoItemsException;
import vibe.ecommerce.common.error.OrderNotFoundException;
import vibe.ecommerce.common.error.ProductNotFoundException;
import vibe.ecommerce.customer.domain.CustomerRepository;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderDetails;
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

  @Transactional
  @Override
  public Order createOrder(Integer customerId) {
    customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    return orderRepo.save(new Order(null, customerId, OrderStatus.NEW, null));
  }

  @Transactional(readOnly = true)
  @Override
  public OrderDetails getOrderDetails(Integer id) {
    Order order = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    List<OrderItem> orderItems = orderRepo.findItems(id);
    Payment payment = orderRepo.findPayment(id).orElse(null);
    return new OrderDetails(order, orderItems, payment);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Order> getOrdersForCustomer(Integer customerId) {
    customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    return orderRepo.findByCustomerId(customerId);
  }

  @Transactional
  @Override
  public OrderItem addOrderItem(AddOrderItemCommand cmd) {
    Product product =
        productRepo
            .findById(cmd.productId())
            .orElseThrow(() -> new ProductNotFoundException(cmd.productId()));
    return orderRepo.addItem(cmd.orderId(), cmd.productId(), cmd.quantity(), product.price());
  }

  @Transactional(readOnly = true)
  @Override
  public List<OrderItem> getOrderItems(Integer orderId) {
    orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    return orderRepo.findItems(orderId);
  }

  @Transactional
  @Override
  public Order cancelOrder(Integer orderId) {
    Order order =
        orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    Order cancelled = order.cancel();
    return orderRepo.save(cancelled);
  }

  @Transactional
  @Override
  public Payment payOrder(Integer orderId, PaymentMethod paymentMethod) {
    Order order =
        orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    if (order.status() != OrderStatus.NEW) {
      throw new OrderCannotBePaidException(orderId);
    }
    List<OrderItem> items = orderRepo.findItems(orderId);
    if (items.isEmpty()) {
      throw new OrderHasNoItemsException(orderId);
    }
    BigDecimal total =
        items.stream()
            .map(oi -> oi.unitPrice().multiply(BigDecimal.valueOf(oi.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    return orderRepo.savePayment(orderId, total, paymentMethod);
  }
}
