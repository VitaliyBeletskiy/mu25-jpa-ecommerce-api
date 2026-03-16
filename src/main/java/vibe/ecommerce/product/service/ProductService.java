package vibe.ecommerce.product.service;

import vibe.ecommerce.product.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
  Product createProduct(String name, BigDecimal price);

  Product getProduct(Integer id);

  List<Product> getProducts();

  Product updateProduct(Integer id, String name, BigDecimal price);

  void deleteProduct(Integer id);
}
