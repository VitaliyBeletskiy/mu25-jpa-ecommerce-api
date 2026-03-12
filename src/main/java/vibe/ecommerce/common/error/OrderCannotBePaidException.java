package vibe.ecommerce.common.error;

public class OrderCannotBePaidException extends RuntimeException {
  public OrderCannotBePaidException(Integer id) {
    super("Order must have status 'NEW' to be paid: " + id);
  }
}
