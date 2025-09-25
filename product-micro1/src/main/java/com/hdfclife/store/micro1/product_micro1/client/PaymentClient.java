//package com.hdfclife.store.micro1.product_micro1.client;
//
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "payment-service", url = "http://localhost:8082")
//public interface PaymentClient {
//
//    @GetMapping("/payments/{orderId}")
//    String getPaymentStatus(@PathVariable("orderId") Long orderId);
//}
