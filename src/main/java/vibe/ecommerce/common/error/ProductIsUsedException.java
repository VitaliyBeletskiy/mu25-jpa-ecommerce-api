package vibe.ecommerce.common.error;

public class ProductIsUsedException extends RuntimeException {
  public ProductIsUsedException(Integer id) {
    super("Product is used: " + id);
  }
}
