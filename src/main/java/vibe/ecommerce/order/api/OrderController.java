package vibe.ecommerce.order.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vibe.ecommerce.order.api.dto.CreateOrderRequest;
import vibe.ecommerce.order.api.dto.OrderMapper;
import vibe.ecommerce.order.api.dto.OrderResponse;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.service.OrderService;

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
}
