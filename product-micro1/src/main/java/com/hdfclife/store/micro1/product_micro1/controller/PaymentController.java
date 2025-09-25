package com.hdfclife.store.micro1.product_micro1.controller;

import com.hdfclife.store.micro1.product_micro1.model.Payment;
import com.hdfclife.store.micro1.product_micro1.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment createdPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Payment> getPaymentStatusByOrderId(@PathVariable Long orderId) {
        Optional<Payment> payment = paymentService.getPaymentStatusByOrderId(orderId);
        return payment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
