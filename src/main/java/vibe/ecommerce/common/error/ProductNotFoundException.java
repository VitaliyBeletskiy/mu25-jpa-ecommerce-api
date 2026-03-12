package vibe.ecommerce.common.error;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Integer id) {
    super("Product not found: " + id);
  }
}
