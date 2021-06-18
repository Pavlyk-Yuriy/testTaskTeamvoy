package com.pavlyk.teamvoy.service.api;

import com.pavlyk.teamvoy.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrderById(Long id);

    void save(Order order);

    List<Order> getAllOrders();

    void removeOrder(Order order);
}
