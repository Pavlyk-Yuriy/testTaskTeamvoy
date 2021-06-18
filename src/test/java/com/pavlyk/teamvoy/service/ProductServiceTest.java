package com.pavlyk.teamvoy.service;

import com.pavlyk.teamvoy.entity.Product;
import com.pavlyk.teamvoy.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;
    @Test
    public void getProductById() {
        Optional<Product> expected = Optional.of(new Product(1L, "name", BigDecimal.valueOf(123.5), new ArrayList<>()));
        when(repository.findById(1L)).thenReturn(expected);
        Optional<Product> actual = service.getProductById(1L);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getProductWithLowestPrice(){
        List<Product> expected = List.of(new Product(1L, "name", BigDecimal.valueOf(123.5), new ArrayList<>()));
        when(repository.findProductsWithLowestPrice("name")).thenReturn(expected);
        List<Product> actual = service.getProductWithLowestPrice("name");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getProductWithLowestPriceTest(){
        List<Product> expected = List.of(new Product(1L, "name", BigDecimal.valueOf(123.5), new ArrayList<>()),
                new Product(1L, "name", BigDecimal.valueOf(123.5), new ArrayList<>()));
        when(repository.findAll()).thenReturn(expected);
        List<Product> actual = service.getProductWithLowestPrice(null);
        Assert.assertEquals(expected,actual);
    }
}
