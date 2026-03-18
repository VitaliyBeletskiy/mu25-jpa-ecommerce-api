package vibe.ecommerce.common.error;

public class OrderCannotBeCancelledException extends RuntimeException {
  public OrderCannotBeCancelledException(Integer id) {
    super("Order cannot be cancelled: " + id);
  }
}
