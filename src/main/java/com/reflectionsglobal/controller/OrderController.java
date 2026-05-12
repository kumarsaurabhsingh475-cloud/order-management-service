package com.reflectionsglobal.controller;

import com.reflectionsglobal.dto.CreateOrderRequest;
import com.reflectionsglobal.dto.OrderResponse;
import com.reflectionsglobal.dto.PaginatedResponse;
import com.reflectionsglobal.dto.UpdateStatusRequest;
import com.reflectionsglobal.model.OrderStatus;
import com.reflectionsglobal.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable String orderId
    ) {
        OrderResponse response = orderService.getOrderById(orderId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable String orderId,
            @Valid @RequestBody UpdateStatusRequest request
    ) {
        OrderResponse response = orderService.updateOrderStatus(orderId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<OrderResponse>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false)
            String customerName
    ) {
        PaginatedResponse<OrderResponse> response = orderService.getAllOrders(page, size, status, customerName);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/dummy")
    public ResponseEntity<String> insertDummyOrders() {
        String response = orderService.insertDummyOrders();
        return ResponseEntity.ok(response);
    }
}