package com.hdfclife.store.micro1.product_micro1.controller;

import com.hdfclife.store.micro1.product_micro1.model.Order;
import com.hdfclife.store.micro1.product_micro1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderWithPaymentStatus(
            @PathVariable String id,
            @RequestParam(name = "client", defaultValue = "resttemplate") String clientType) {
        return orderService.getOrderWithPaymentStatus(id, clientType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
