package com.pavlyk.teamvoy.controller;

import com.pavlyk.teamvoy.entity.Product;
import com.pavlyk.teamvoy.service.api.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/minprice")
    public List<Product> getItemWithMinPrice() {
        return productService.getProductsByMinPrice();
    }

    @GetMapping("/maxprice")
    public List<Product> getItemWithMaxPrice() {
        return productService.getProductsByMinPrice();
    }

    @GetMapping("/getItem")
    public List<Product> getProductWithLowestPrice(@RequestParam(required = false) String name) {
        return productService.getProductWithLowestPrice(name);
    }

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new NoSuchElementException();
    }
}
