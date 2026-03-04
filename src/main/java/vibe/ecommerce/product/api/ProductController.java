package vibe.ecommerce.product.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vibe.ecommerce.product.api.dto.CreateProductRequest;
import vibe.ecommerce.product.api.dto.ProductMapper;
import vibe.ecommerce.product.api.dto.ProductResponse;
import vibe.ecommerce.product.domain.Product;
import vibe.ecommerce.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService service;

  public ProductController(final ProductService productService) {
    this.service = productService;
  }

  @PostMapping
  public ProductResponse createProduct(@RequestBody CreateProductRequest request) {
    Product product = service.createProduct(request.name(), request.price());
    return ProductMapper.toResponse(product);
  }

  @GetMapping("/{id}")
  public ProductResponse getProduct(@PathVariable Integer id) {
    Product product = service.getProduct(id);
    return ProductMapper.toResponse(product);
  }

  @GetMapping
  public List<ProductResponse> getProducts() {
    return service.getProducts().stream().map(ProductMapper::toResponse).toList();
  }
}
