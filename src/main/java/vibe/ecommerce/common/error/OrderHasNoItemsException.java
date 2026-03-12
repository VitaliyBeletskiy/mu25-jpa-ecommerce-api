package vibe.ecommerce.common.error;

public class OrderHasNoItemsException extends RuntimeException {
  public OrderHasNoItemsException(Integer id) {
    super("Order has no items: " + id);
  }
}
