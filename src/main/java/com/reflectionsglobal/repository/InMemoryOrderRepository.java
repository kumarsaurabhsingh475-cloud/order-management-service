package com.reflectionsglobal.repository;

import com.reflectionsglobal.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final ConcurrentHashMap<String, Order> orderStorage = new ConcurrentHashMap<>();

    @Override
    public Order save(Order order) {

        orderStorage.put(order.getOrderId(), order);

        return order;
    }

    @Override
    public Optional<Order> findById(String orderId) {

        return Optional.ofNullable(orderStorage.get(orderId));
    }

    @Override
    public List<Order> findAll() {

        return new ArrayList<>(orderStorage.values());
    }

    @Override
    public boolean existsById(String orderId) {

        return orderStorage.containsKey(orderId);
    }
}