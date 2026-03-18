package vibe.ecommerce.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<String> handle(OrderNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<String> handle(CustomerNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<String> handle(ProductNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(OrderCannotBePaidException.class)
  public ResponseEntity<String> handle(OrderCannotBePaidException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(OrderHasNoItemsException.class)
  public ResponseEntity<String> handle(OrderHasNoItemsException e) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
  }

  @ExceptionHandler(ProductIsUsedException.class)
  public ResponseEntity<String> handle(ProductIsUsedException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(OrderCannotBeCancelledException.class)
  public ResponseEntity<String> handle(OrderCannotBeCancelledException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }
}
