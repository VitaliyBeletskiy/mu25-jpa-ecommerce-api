package vibe.ecommerce.product.persistence.inmemory;

import org.springframework.stereotype.Repository;
import vibe.ecommerce.product.domain.Product;
import vibe.ecommerce.product.domain.ProductRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {

  private final Map<Integer, Product> storage = new HashMap<>();
  private int currentId = 0;

  @Override
  public Product save(Product product) {
    currentId++;
    LocalDateTime now = LocalDateTime.now();
    Product toSave = new Product(currentId, product.name(), product.price(), now);
    storage.put(currentId, toSave);
    return toSave;
  }

  @Override
  public Optional<Product> findById(Integer id) {
    if (storage.containsKey(id)) {
      return Optional.of(storage.get(id));
    }
    return Optional.empty();
  }

  @Override
  public List<Product> findAll() {
    return storage.values().stream().toList();
  }
}
