# E-Commerce API

Minimal REST API for managing products, customers, and orders.\
Built with Spring Boot, following layered architecture principles and
repository abstraction (supports both InMemory and JPA implementations).

------------------------------------------------------------------------

## Tech Stack

-   Java 21
-   Spring Boot
-   Spring Web
-   Spring Data JPA
-   MySQL (or InMemory repositories for development)

------------------------------------------------------------------------

## Domain Overview

The system supports:

-   Customers
-   Products
-   Orders
-   Order Items
-   Payments

Relationships:

-   Customer **1 → many** Orders\
-   Order **many → many** Products (via OrderItem)\
-   Order **1 → 1** Payment

------------------------------------------------------------------------

## API Endpoints

### Products
-   `POST   /products`  CreateProductRequest -> ProductResponse
-   `GET    /products`
-   `GET    /products/{id}`

### Customers
-   `POST   /customers` CreateCustomerRequest -> CustomerResponse
-   `GET    /customers`
-   `GET    /customers/{id}`

### Orders
-   `POST   /orders`  CreateOrderRequest -> OrderResponse
-   `GET    /orders/{id}` ... -> OrderDetailsResponse
-   `GET    /customers/{customerId}/orders`

### Order Items
-   `POST   /orders/{orderId}/items`   AddOrderItemRequest -> OrderItemResponse
-   `GET    /orders/{orderId}/items`
-   `DELETE /orders/{orderId}/items/{productId}`

### Payments
-   `POST   /orders/{orderId}/pay`  PayOrderRequest -> PaymentResponse

------------------------------------------------------------------------

## Architecture

The project follows a layered structure:

api → Controllers & DTOs\
service → Business logic\
domain → Core models & repository interfaces\
persistence\
├─ inmemory\
└─ jpa

Repository interfaces are defined in the domain layer, allowing:

-   InMemory implementations for testing
-   JPA/MySQL implementations for production

------------------------------------------------------------------------

## Run Configuration

Example `application.properties`:

    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=validate

------------------------------------------------------------------------

## Development Notes

-   Use `ddl-auto=validate`
-   Spring Profiles can be used to switch between InMemory and JPA
    repositories
