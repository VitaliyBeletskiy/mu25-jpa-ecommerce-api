package vibe.ecommerce.product.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vibe.ecommerce.product.api.dto.UpsertProductRequest;
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
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponse createProduct(@RequestBody @Valid UpsertProductRequest request) {
    Product product = service.createProduct(request.name(), request.price());
    return ProductMapper.toResponse(product);
  }

  @GetMapping("/{id}")
  public ProductResponse getProduct(@PathVariable Integer productId) {
    Product product = service.getProduct(productId);
    return ProductMapper.toResponse(product);
  }

  @GetMapping
  public List<ProductResponse> getProducts() {
    return service.getProducts().stream().map(ProductMapper::toResponse).toList();
  }

  @PutMapping("/{productId}")
  public ProductResponse updateProduct(
      @PathVariable Integer productId, @RequestBody @Valid UpsertProductRequest request) {
    Product product = service.updateProduct(productId, request.name(), request.price());
    return ProductMapper.toResponse(product);
  }
}
