package vibe.ecommerce.product.persistence.jpa;

import vibe.ecommerce.product.domain.Product;

public class ProductEntityMapper {

  private ProductEntityMapper() {}

  public static ProductEntity toEntity(Product product) {
    return new ProductEntity(product.id(), product.name(), product.price(), product.createdAt());
  }

  public static Product toDomain(ProductEntity productEntity) {
    return new Product(
        productEntity.getId(),
        productEntity.getName(),
        productEntity.getPrice(),
        productEntity.getCreatedAt());
  }
}
