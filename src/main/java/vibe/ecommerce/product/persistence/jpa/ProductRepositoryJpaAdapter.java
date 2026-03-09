package vibe.ecommerce.product.persistence.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import vibe.ecommerce.product.domain.Product;
import vibe.ecommerce.product.domain.ProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.persistence.mode", havingValue = "jpa", matchIfMissing = true)
public class ProductRepositoryJpaAdapter implements ProductRepository {

  private final ProductJpaRepository jpaRepo;

  @PersistenceContext private EntityManager entityManager;

  public ProductRepositoryJpaAdapter(ProductJpaRepository productJpaRepository) {
    this.jpaRepo = productJpaRepository;
  }

  @Override
  public Product save(Product product) {
    ProductEntity entity = ProductEntityMapper.toEntity(product);
    ProductEntity saved = jpaRepo.save(entity);
    entityManager.refresh(saved);
    return ProductEntityMapper.toDomain(saved);
  }

  @Override
  public Optional<Product> findById(Integer id) {
    return jpaRepo.findById(id).map(ProductEntityMapper::toDomain);
  }

  @Override
  public List<Product> findAll() {
    return jpaRepo.findAll().stream().map(ProductEntityMapper::toDomain).toList();
  }
}
