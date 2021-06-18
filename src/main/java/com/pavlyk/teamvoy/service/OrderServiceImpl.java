package com.pavlyk.teamvoy.service;

import com.pavlyk.teamvoy.entity.Order;
import com.pavlyk.teamvoy.repository.OrderRepository;
import com.pavlyk.teamvoy.service.api.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order){
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders(){
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public void removeOrder(Order order){
        orderRepository.delete(order);
    }
}
