package com.pavlyk.teamvoy.service;
import com.pavlyk.teamvoy.entity.Order;
import com.pavlyk.teamvoy.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {


    @InjectMocks
    private OrderServiceImpl service;

    @Mock
    private OrderRepository repository;

    @Test
    public void removeOrderTest() {
        Order order = new Order(3L,127.5,3, LocalTime.now(),new ArrayList<>());
        service.removeOrder(order);
        verify(repository,times(1)).delete(eq(order));
    }

    @Test
    public void saveTest() {
        Order order = new Order(3L,127.5,3, LocalTime.now(),new ArrayList<>());
        service.save(order);
        verify(repository,times(1)).save(eq(order));
    }


    @Test
    public void getAllTest() {
        List<Order> expected = List.of(new Order(1L,123.5,3, LocalTime.now(),new ArrayList<>()),
                new Order(2L,123.5,3, LocalTime.now(),new ArrayList<>()));
        when(repository.findAll()).thenReturn(expected);
        List<Order> actual = service.getAllOrders();
        Assert.assertEquals(expected,actual);
    }
}
