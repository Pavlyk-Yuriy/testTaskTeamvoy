package com.pavlyk.teamvoy.repository;

import com.pavlyk.teamvoy.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query(value = "select * from teamvoy.product p where price = (select min(price) from teamvoy.product)",
            nativeQuery = true)
    List<Product> getProductsByMinPrice();

    @Query(value = "select * from teamvoy.product p where price = (select max(price) from teamvoy.product)",
            nativeQuery = true)
    List<Product> getProductsByMaxPrice();

    @Query(value = "select * from teamvoy.product p where price " +
            "= (select min(price) from teamvoy.product where name =:name) and name =:name",
            nativeQuery = true)
    List<Product> findProductsWithLowestPrice(String name);
}
