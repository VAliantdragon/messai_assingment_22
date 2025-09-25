package com.hdfclife.store.micro1.product_micro1.repository;

import com.hdfclife.store.micro1.product_micro1.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
