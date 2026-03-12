package vibe.ecommerce.common.error;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(Integer id) {
    super("Customer not found: " + id);
  }
}
