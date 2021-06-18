package com.pavlyk.teamvoy.repository;

import com.pavlyk.teamvoy.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
}
