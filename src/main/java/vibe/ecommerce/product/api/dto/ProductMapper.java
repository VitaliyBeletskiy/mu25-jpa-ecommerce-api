package vibe.ecommerce.product.api.dto;

import vibe.ecommerce.product.domain.Product;

public class ProductMapper {
  public static ProductResponse toResponse(Product product) {
    return new ProductResponse(product.id(), product.name(), product.price(), product.createdAt());
  }
}
