package vibe.ecommerce.order.persistence.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import vibe.ecommerce.customer.persistence.jpa.CustomerEntity;
import vibe.ecommerce.customer.persistence.jpa.CustomerJpaRepository;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.domain.OrderRepository;
import vibe.ecommerce.order.domain.Payment;
import vibe.ecommerce.order.domain.PaymentMethod;
import vibe.ecommerce.product.persistence.jpa.ProductEntity;
import vibe.ecommerce.product.persistence.jpa.ProductJpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.persistence.mode", havingValue = "jpa", matchIfMissing = true)
public class OrderRepositoryJpaAdapter implements OrderRepository {

  private final OrderJpaRepository orderJpaRepo;
  private final OrderItemJpaRepository orderItemJpaRepo;
  private final PaymentJpaRepository paymentJpaRepo;
  private final CustomerJpaRepository customerJpaRepo;
  private final ProductJpaRepository productJpaRepo;

  @PersistenceContext private EntityManager entityManager;

  public OrderRepositoryJpaAdapter(
      OrderJpaRepository orderJpaRepository,
      OrderItemJpaRepository orderItemJpaRepository,
      PaymentJpaRepository paymentJpaRepository,
      CustomerJpaRepository customerJpaRepository,
      ProductJpaRepository productJpaRepository) {
    this.orderJpaRepo = orderJpaRepository;
    this.orderItemJpaRepo = orderItemJpaRepository;
    this.paymentJpaRepo = paymentJpaRepository;
    this.customerJpaRepo = customerJpaRepository;
    this.productJpaRepo = productJpaRepository;
  }

  @Override
  public Order save(Order order) {
    CustomerEntity customer = customerJpaRepo.findById(order.customerId()).orElseThrow();
    OrderEntity entity = new OrderEntity(customer, order.status());
    OrderEntity saved = orderJpaRepo.saveAndFlush(entity);
    entityManager.refresh(saved);
    return OrderEntityMapper.toDomain(saved);
  }

  @Override
  public Optional<Order> findById(Integer id) {
    return orderJpaRepo.findById(id).map(OrderEntityMapper::toDomain);
  }

  @Override
  public List<Order> findByCustomerId(Integer customerId) {
    return orderJpaRepo.findByCustomer_Id(customerId).stream()
        .map(OrderEntityMapper::toDomain)
        .toList();
  }

  @Override
  public OrderItem addItem(Integer orderId, Integer productId, Integer quantity, BigDecimal unitPrice) {
    OrderEntity order = orderJpaRepo.findById(orderId).orElseThrow();
    ProductEntity product = productJpaRepo.findById(productId).orElseThrow();
    OrderItemEntity entity = new OrderItemEntity(order, product, quantity, unitPrice);
    OrderItemEntity saved = orderItemJpaRepo.save(entity);
    //    entityManager.refresh(saved);  - not needed since we don't have DEFAULT CURRENT_TIMESTAMP
    return OrderItemEntityMapper.toDomain(saved);
  }

  @Override
  public List<OrderItem> findItems(Integer orderId) {
    return orderItemJpaRepo.findByOrder_Id(orderId).stream()
        .map(OrderItemEntityMapper::toDomain)
        .toList();
  }

  @Override
  public Payment savePayment(Integer orderId, BigDecimal amount, PaymentMethod method) {
    OrderEntity order = orderJpaRepo.findById(orderId).orElseThrow();
    PaymentEntity entity = new PaymentEntity(order, amount, method);
    PaymentEntity saved = paymentJpaRepo.saveAndFlush(entity);
    entityManager.refresh(saved);
    order.markPaid();
    return PaymentEntityMapper.toDomain(saved);
  }

  @Override
  public Optional<Payment> findPayment(Integer orderId) {
    return paymentJpaRepo.findByOrder_Id(orderId).map(PaymentEntityMapper::toDomain);
  }
}
