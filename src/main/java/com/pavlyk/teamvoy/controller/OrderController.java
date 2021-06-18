package com.pavlyk.teamvoy.controller;

import com.pavlyk.teamvoy.entity.Order;
import com.pavlyk.teamvoy.entity.Product;
import com.pavlyk.teamvoy.service.api.OrderService;
import com.pavlyk.teamvoy.service.api.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/getAll")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/getById/{id}")
    public Order getOrder(@PathVariable Long id){
        Optional<Order> order = orderService.getOrderById(id);
        if(order.isPresent()){
            return order.get();
        }
        throw new NoSuchElementException();
    }

    //creating orders
    @GetMapping("/createOrder/{productId1}/{productId2}/{productId3}")
    public void createOrder(@PathVariable Long productId1, @PathVariable Long productId2,
                            @PathVariable Long productId3){
        List<Product> list = getProductListForOrder(productId1, productId2, productId3);
        Order order = Order.builder()
                .orderTime(LocalTime.now())
                .products(list)
                .quantity(list.size())
                .totalPrice(list.stream().mapToDouble(el->el.getPrice().doubleValue()).sum())
                .build();
        orderService.save(order);
    }

    //The order is valid for 10 minutes (not valid orders can be deleted).
    @GetMapping("/check")
    public void checkingOrderWithDeleting(){
        List<Order> orders = orderService.getAllOrders();
        for (Order order: orders) {
            if(Duration.between(order.getOrderTime(), LocalTime.now()).toMinutes() > 10){
                order.setProducts(null);
                orderService.removeOrder(order);
            }
        }
    }

    private List<Product> getProductListForOrder(Long productId1, Long productId2, Long productId3) {
        Optional<Product> product1 = productService.getProductById(productId1);
        Optional<Product> product2 = productService.getProductById(productId2);
        Optional<Product> product3 = productService.getProductById(productId3);
        List<Product> list = new ArrayList<>();
        product1.ifPresent(list::add);
        product2.ifPresent(list::add);
        product3.ifPresent(list::add);
        return list;
    }
}
