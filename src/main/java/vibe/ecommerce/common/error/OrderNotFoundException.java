package vibe.ecommerce.common.error;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(Integer id) {
    super("Order not found: " + id);
  }
}
