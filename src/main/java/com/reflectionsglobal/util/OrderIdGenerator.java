package com.reflectionsglobal.util;

import java.util.UUID;

public final class OrderIdGenerator {

    private OrderIdGenerator() {
    }

    public static String generateOrderId() {
        return UUID.randomUUID().toString();
    }
}