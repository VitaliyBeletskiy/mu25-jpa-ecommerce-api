package vibe.ecommerce.product.service;

import org.springframework.stereotype.Service;
import vibe.ecommerce.product.domain.Product;
import vibe.ecommerce.product.domain.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultProductService implements ProductService {

  private final ProductRepository repo;

  public DefaultProductService(ProductRepository productRepository) {
    this.repo = productRepository;
  }

  @Override
  public Product createProduct(String name, BigDecimal price) {
    return repo.save(new Product(null, name, price, null));
  }

  @Override
  public Product getProduct(Integer id) {
    return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
  }

  @Override
  public List<Product> getProducts() {
    return repo.findAll();
  }
}
