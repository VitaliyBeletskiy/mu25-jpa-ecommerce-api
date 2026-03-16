package vibe.ecommerce.customer.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vibe.ecommerce.customer.api.dto.UpsertCustomerRequest;
import vibe.ecommerce.customer.api.dto.CustomerMapper;
import vibe.ecommerce.customer.api.dto.CustomerResponse;
import vibe.ecommerce.customer.domain.Customer;
import vibe.ecommerce.customer.service.CustomerService;
import vibe.ecommerce.order.api.dto.OrderMapper;
import vibe.ecommerce.order.api.dto.OrderResponse;
import vibe.ecommerce.order.domain.Order;
import vibe.ecommerce.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;
  private final OrderService orderService;

  public CustomerController(
      final CustomerService customerService, final OrderService orderService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerResponse createCustomer(@RequestBody @Valid UpsertCustomerRequest request) {
    Customer customer = customerService.createCustomer(request.fullName(), request.email());
    return CustomerMapper.toResponse(customer);
  }

  @GetMapping("/{customerId}")
  public CustomerResponse getCustomer(@PathVariable Integer customerId) {
    Customer customer = customerService.getCustomer(customerId);
    return CustomerMapper.toResponse(customer);
  }

  @GetMapping
  public List<CustomerResponse> getCustomers() {
    List<Customer> customers = customerService.getCustomers();
    return customers.stream().map(CustomerMapper::toResponse).toList();
  }

  @GetMapping("/{customerId}/orders")
  public List<OrderResponse> getOrdersForCustomer(@PathVariable Integer customerId) {
    List<Order> orders = orderService.getOrdersForCustomer(customerId);
    return orders.stream().map(OrderMapper::toOrderResponse).toList();
  }

  @PutMapping("/{customerId}")
  public CustomerResponse updateCustomer(
      @PathVariable Integer customerId, @RequestBody @Valid UpsertCustomerRequest request) {
    Customer customer =
        customerService.updateCustomer(customerId, request.fullName(), request.email());
    return CustomerMapper.toResponse(customer);
  }

  @DeleteMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable Integer customerId) {
    customerService.deleteCustomer(customerId);
  }
}
