package vibe.ecommerce.order.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vibe.ecommerce.order.api.dto.AddOrderItemRequest;
import vibe.ecommerce.order.api.dto.CreateOrderRequest;
import vibe.ecommerce.order.api.dto.OrderDetailsResponse;
import vibe.ecommerce.order.api.dto.OrderItemResponse;
import vibe.ecommerce.order.api.dto.OrderMapper;
import vibe.ecommerce.order.api.dto.OrderResponse;
import vibe.ecommerce.order.api.dto.PayOrderRequest;
import vibe.ecommerce.order.api.dto.PaymentResponse;
import vibe.ecommerce.order.service.AddOrderItemCommand;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderDetails;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.domain.Payment;
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
  @ResponseStatus(HttpStatus.CREATED)
  public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
    Order order = service.createOrder(request.customerId());
    return OrderMapper.toOrderResponse(order);
  }

  @GetMapping("/{id}")
  public OrderDetailsResponse getOrder(@PathVariable Integer id) {
    OrderDetails orderDetails = service.getOrderDetails(id);
    return OrderMapper.toOrderDetailsResponse(orderDetails);
  }

  @PostMapping("/{orderId}/items")
  public OrderItemResponse addOrderItem(
      @PathVariable Integer orderId, @RequestBody @Valid AddOrderItemRequest request) {
    OrderItem orderItem =
        service.addOrderItem(
            new AddOrderItemCommand(orderId, request.productId(), request.quantity()));
    return OrderMapper.toOrderItemResponse(orderItem);
  }

  @GetMapping("/{orderId}/items")
  public List<OrderItemResponse> getOrderItems(@PathVariable Integer orderId) {
    List<OrderItem> orderItems = service.getOrderItems(orderId);
    return orderItems.stream().map(OrderMapper::toOrderItemResponse).toList();
  }

  @PatchMapping("/{orderId}/cancel")
  public OrderResponse cancelOrder(@PathVariable Integer orderId) {
    Order order = service.cancelOrder(orderId);
    return OrderMapper.toOrderResponse(order);
  }

  @PostMapping("/{orderId}/pay")
  public PaymentResponse payOrder(
      @PathVariable Integer orderId, @RequestBody @Valid PayOrderRequest request) {
    Payment payment = service.payOrder(orderId, request.paymentMethod());
    return OrderMapper.toPaymentResponse(payment);
  }
}
