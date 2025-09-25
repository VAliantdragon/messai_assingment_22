package com.hdfclife.store.micro1.product_micro1.repository;

import com.hdfclife.store.micro1.product_micro1.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Long> {
    Optional<Payment> findByOrderId(Long orderId);
}
