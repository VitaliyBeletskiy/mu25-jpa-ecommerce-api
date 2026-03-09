package vibe.ecommerce.product.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {}
