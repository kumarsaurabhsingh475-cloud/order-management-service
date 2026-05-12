package com.reflectionsglobal.service;

import com.reflectionsglobal.dto.CreateOrderRequest;
import com.reflectionsglobal.dto.OrderResponse;
import com.reflectionsglobal.dto.PaginatedResponse;
import com.reflectionsglobal.dto.UpdateStatusRequest;
import com.reflectionsglobal.model.OrderStatus;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(String orderId);

    OrderResponse updateOrderStatus(
            String orderId,
            UpdateStatusRequest request
    );

    PaginatedResponse<OrderResponse> getAllOrders(
            int page,
            int size,
            OrderStatus status,
            String customerName
    );

    String insertDummyOrders();
}