package com.reflectionsglobal.model;

import java.time.LocalDateTime;

public class Order {

    private String orderId;
    private String customerName;
    private Double amount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(String orderId,
                 String customerName,
                 Double amount,
                 OrderStatus status,
                 LocalDateTime createdAt) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}