package com.reflectionsglobal.specification;

import com.reflectionsglobal.model.Order;
import com.reflectionsglobal.model.OrderStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class OrderSpecification {

    private OrderSpecification() {
    }

    public static List<Order> filterOrders(
            List<Order> orders,
            OrderStatus status,
            String customerName
    ) {

        return orders.stream()

                .filter(order -> {

                    if (Objects.isNull(status)) {
                        return true;
                    }

                    return order.getStatus() == status;
                })

                .filter(order -> {

                    if (customerName == null || customerName.isBlank()) {
                        return true;
                    }

                    return order.getCustomerName()
                            .toLowerCase()
                            .contains(customerName.toLowerCase());
                })

                .collect(Collectors.toList());
    }
}