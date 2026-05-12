package com.reflectionsglobal.repository;

import com.reflectionsglobal.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(String orderId);

    List<Order> findAll();

    boolean existsById(String orderId);
}