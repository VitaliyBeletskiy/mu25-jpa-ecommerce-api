package vibe.ecommerce.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vibe.ecommerce.common.error.ProductNotFoundException;
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

  @Transactional
  @Override
  public Product createProduct(String name, BigDecimal price) {
    return repo.save(new Product(null, name, price, null));
  }

  @Transactional(readOnly = true)
  @Override
  public Product getProduct(Integer id) {
    return repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Transactional(readOnly = true)
  @Override
  public List<Product> getProducts() {
    return repo.findAll();
  }

  @Transactional
  @Override
  public Product updateProduct(Integer id, String name, BigDecimal price) {
    repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    return repo.save(new Product(id, name, price));
  }
}
