package vibe.ecommerce.product.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
  Product save(Product product);

  Optional<Product> findById(Integer id);

  List<Product> findAll();

  void delete(Integer id);
}
