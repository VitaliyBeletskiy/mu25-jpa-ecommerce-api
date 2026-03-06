package vibe.ecommerce.order.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vibe.ecommerce.order.api.dto.AddOrderItemRequest;
import vibe.ecommerce.order.api.dto.CreateOrderRequest;
import vibe.ecommerce.order.api.dto.OrderItemResponse;
import vibe.ecommerce.order.api.dto.OrderMapper;
import vibe.ecommerce.order.api.dto.OrderResponse;
import vibe.ecommerce.order.api.dto.PayOrderRequest;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService service;

  public OrderController(final OrderService orderService) {
    this.service = orderService;
  }

  @PostMapping
  public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
    Order order = service.createOrder(request.customerId());
    return OrderMapper.toOrderResponse(order);
  }

  @GetMapping("/{id}")
  public OrderResponse getOrder(@PathVariable Integer id) {
    Order order = service.getOrder(id);
    return OrderMapper.toOrderResponse(order);
  }

  @PostMapping("/{orderId}/items")
  public OrderItemResponse addOrderItem(
      @PathVariable Integer orderId, @RequestBody @Valid AddOrderItemRequest request) {
    OrderItem orderItem = service.addOrderItem(orderId, request.productId(), request.quantity());
    return OrderMapper.toOrderItemResponse(orderItem);
  }

  @GetMapping("/{orderId}/items")
  public List<OrderItemResponse> getOrderItems(@PathVariable Integer orderId) {
    List<OrderItem> orderItems = service.getOrderItems(orderId);
    return orderItems.stream().map(OrderMapper::toOrderItemResponse).toList();
  }

  @PostMapping("/{orderId}/pay")
  public OrderResponse payOrder(
      @PathVariable Integer orderId, @RequestBody @Valid PayOrderRequest request) {
    Order order = service.payOrder(orderId, request.amount(), request.paymentMethod());
    return OrderMapper.toOrderResponse(order);
  }
}
