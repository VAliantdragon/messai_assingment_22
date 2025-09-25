package com.hdfclife.store.micro1.product_micro1.service;

import com.hdfclife.store.micro1.product_micro1.model.Payment;
import com.hdfclife.store.micro1.product_micro1.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentStatusByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
