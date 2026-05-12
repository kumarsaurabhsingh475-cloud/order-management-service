package com.reflectionsglobal.service;

import com.reflectionsglobal.dto.CreateOrderRequest;
import com.reflectionsglobal.dto.OrderResponse;
import com.reflectionsglobal.dto.UpdateStatusRequest;
import com.reflectionsglobal.exception.InvalidStatusTransitionException;
import com.reflectionsglobal.model.OrderStatus;
import com.reflectionsglobal.repository.InMemoryOrderRepository;
import com.reflectionsglobal.repository.OrderRepository;
import com.reflectionsglobal.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        OrderRepository repository = new InMemoryOrderRepository();
        orderService = new OrderServiceImpl(repository);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        CreateOrderRequest request = new CreateOrderRequest("Saurabh", 5000.0);
        OrderResponse response = orderService.createOrder(request);
        assertNotNull(response);
        assertNotNull(response.getOrderId());
        assertEquals("Saurabh", response.getCustomerName());
        assertEquals(OrderStatus.NEW, response.getStatus());
    }

    @Test
    void shouldUpdateStatusSuccessfully() {
        CreateOrderRequest createRequest = new CreateOrderRequest("Saurabh", 2000.0);
        OrderResponse createdOrder = orderService.createOrder(createRequest);
        UpdateStatusRequest updateRequest = new UpdateStatusRequest(OrderStatus.PROCESSING);
        OrderResponse updatedOrder = orderService.updateOrderStatus(createdOrder.getOrderId(), updateRequest);
        assertEquals(OrderStatus.PROCESSING, updatedOrder.getStatus());
    }

    @Test
    void shouldThrowExceptionForInvalidStatusTransition() {
        CreateOrderRequest createRequest = new CreateOrderRequest("Saurabh", 3000.0);
        OrderResponse createdOrder = orderService.createOrder(createRequest);
        UpdateStatusRequest updateRequest = new UpdateStatusRequest(OrderStatus.COMPLETED);
        assertThrows(InvalidStatusTransitionException.class, () -> orderService.updateOrderStatus(createdOrder.getOrderId(), updateRequest));
    }
}