package vibe.ecommerce.customer.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vibe.ecommerce.customer.api.dto.CreateCustomerRequest;
import vibe.ecommerce.customer.api.dto.CustomerMapper;
import vibe.ecommerce.customer.api.dto.CustomerResponse;
import vibe.ecommerce.customer.domain.Customer;
import vibe.ecommerce.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(final CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public CustomerResponse createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
    Customer customer = customerService.createCustomer(request.fullName(), request.email());
    return CustomerMapper.map(customer);
  }
}
