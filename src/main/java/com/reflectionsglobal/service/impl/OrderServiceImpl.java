package com.reflectionsglobal.service.impl;

import com.reflectionsglobal.dto.CreateOrderRequest;
import com.reflectionsglobal.dto.OrderResponse;
import com.reflectionsglobal.dto.PaginatedResponse;
import com.reflectionsglobal.dto.UpdateStatusRequest;
import com.reflectionsglobal.exception.InvalidStatusTransitionException;
import com.reflectionsglobal.exception.OrderNotFoundException;
import com.reflectionsglobal.model.Order;
import com.reflectionsglobal.model.OrderStatus;
import com.reflectionsglobal.repository.OrderRepository;
import com.reflectionsglobal.service.OrderService;
import com.reflectionsglobal.specification.OrderSpecification;
import com.reflectionsglobal.util.OrderIdGenerator;
import com.reflectionsglobal.util.PaginationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderId(OrderIdGenerator.generateOrderId());
        order.setCustomerName(request.getCustomerName());
        order.setAmount(request.getAmount());
        order.setStatus(OrderStatus.NEW);
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        return mapToResponse(order);
    }

    @Override
    public OrderResponse updateOrderStatus(
            String orderId,
            UpdateStatusRequest request
    ) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        validateStatusTransition(order.getStatus(), request.getStatus());
        order.setStatus(request.getStatus());
        Order updatedOrder = orderRepository.save(order);
        return mapToResponse(updatedOrder);
    }

    @Override
    public PaginatedResponse<OrderResponse> getAllOrders(
            int page,
            int size,
            OrderStatus status,
            String customerName
    ) {
        List<Order> allOrders = orderRepository.findAll();
        List<Order> filteredOrders = OrderSpecification.filterOrders(allOrders, status, customerName);
        List<Order> paginatedOrders = PaginationUtil.paginate(filteredOrders, page, size);
        List<OrderResponse> responseList = paginatedOrders.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        long totalElements = filteredOrders.size();
        int totalPages = size == 0 ? 0 : (int) Math.ceil((double) totalElements / size);
        return new PaginatedResponse<>(responseList, page, size, totalElements, totalPages);
    }

    @Override
    public String insertDummyOrders() {
        Random random = new Random();
        for (int i = 1; i <= 500; i++) {
            Order order = new Order();
            order.setOrderId(OrderIdGenerator.generateOrderId());
            order.setCustomerName("DummyCustomer-" + i);
            order.setAmount(100 + (1000 * random.nextDouble()));
            order.setStatus(OrderStatus.values()[random.nextInt(OrderStatus.values().length)]);
            order.setCreatedAt(LocalDateTime.now());
            orderRepository.save(order);
        }
        return "500 dummy orders inserted successfully";
    }

    private void validateStatusTransition(
            OrderStatus currentStatus,
            OrderStatus newStatus
    ) {
        boolean isValidTransition = (currentStatus == OrderStatus.NEW && newStatus == OrderStatus.PROCESSING)
                || (currentStatus == OrderStatus.PROCESSING && newStatus == OrderStatus.COMPLETED);

        if (!isValidTransition) {
            throw new InvalidStatusTransitionException("Invalid status transition from " + currentStatus + " to " + newStatus);
        }
    }

    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(order.getOrderId(), order.getCustomerName(), order.getAmount(), order.getStatus(), order.getCreatedAt());
    }
}