package vibe.ecommerce.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vibe.ecommerce.common.error.ProductIsUsedException;
import vibe.ecommerce.common.error.ProductNotFoundException;
import vibe.ecommerce.order.domain.OrderRepository;
import vibe.ecommerce.product.domain.Product;
import vibe.ecommerce.product.domain.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultProductService implements ProductService {

  private final ProductRepository productRepo;
  private final OrderRepository orderRepo;

  public DefaultProductService(
      final ProductRepository productRepository, final OrderRepository orderRepo) {
    this.productRepo = productRepository;
    this.orderRepo = orderRepo;
  }

  @Transactional
  @Override
  public Product createProduct(String name, BigDecimal price) {
    return productRepo.save(new Product(null, name, price, null));
  }

  @Transactional(readOnly = true)
  @Override
  public Product getProduct(Integer id) {
    return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Transactional(readOnly = true)
  @Override
  public List<Product> getProducts() {
    return productRepo.findAll();
  }

  @Transactional
  @Override
  public Product updateProduct(Integer id, String name, BigDecimal price) {
    productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    return productRepo.save(new Product(id, name, price));
  }

  @Transactional
  @Override
  public void deleteProduct(Integer id) {
    productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    if (orderRepo.existsByProductId(id)) {
      throw new ProductIsUsedException(id);
    }
    productRepo.delete(id);
  }
}
