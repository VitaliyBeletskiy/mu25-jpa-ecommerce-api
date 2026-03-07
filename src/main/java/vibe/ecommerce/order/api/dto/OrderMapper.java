package vibe.ecommerce.order.api.dto;

import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.domain.OrderDetails;
import vibe.ecommerce.order.domain.OrderItem;
import vibe.ecommerce.order.domain.Payment;

public class OrderMapper {

  private OrderMapper() {}

  public static OrderResponse toOrderResponse(Order order) {
    return new OrderResponse(order.id(), order.customerId(), order.status(), order.createdAt());
  }

  public static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
    return new OrderItemResponse(
        orderItem.orderId(), orderItem.productId(), orderItem.quantity(), orderItem.unitPrice());
  }

  public static PaymentResponse toPaymentResponse(Payment payment) {
    return new PaymentResponse(
        payment.orderId(), payment.amount(), payment.method(), payment.paidAt());
  }

  public static OrderDetailsResponse toOrderDetailsResponse(OrderDetails orderDetails) {
    return new OrderDetailsResponse(
        orderDetails.order().id(),
        orderDetails.order().customerId(),
        orderDetails.order().status(),
        orderDetails.order().createdAt(),
        orderDetails.items().stream().map(OrderMapper::toOrderItemResponse).toList(),
        orderDetails.payment() != null ? toPaymentResponse(orderDetails.payment()) : null);
  }
}
