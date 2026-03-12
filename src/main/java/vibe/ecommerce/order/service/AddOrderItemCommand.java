package vibe.ecommerce.order.service;

public record AddOrderItemCommand(Integer orderId, Integer productId, Integer quantity) {}
