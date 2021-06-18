package com.pavlyk.teamvoy.service;

import com.pavlyk.teamvoy.entity.Product;
import com.pavlyk.teamvoy.repository.ProductRepository;
import com.pavlyk.teamvoy.service.api.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProductsByMinPrice() {
        return productRepository.getProductsByMinPrice();
    }

    @Override
    public List<Product> getProductsByMaxPrice() {
        return productRepository.getProductsByMaxPrice();
    }

    @Override
    public List<Product> getProductWithLowestPrice(String name) {
        if(name == null){
            return (List<Product>) productRepository.findAll();
        }
        return productRepository.findProductsWithLowestPrice(name);
    }

    @Override
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
}
