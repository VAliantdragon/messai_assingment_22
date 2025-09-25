package com.hdfclife.store.product.store.repository;

import com.hdfclife.store.product.store.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
}
