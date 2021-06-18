package com.pavlyk.teamvoy.service.api;

import com.pavlyk.teamvoy.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProductsByMinPrice();

    List<Product> getProductsByMaxPrice();

    List<Product> getProductWithLowestPrice(String name);

    Optional<Product> getProductById(Long id);
}
