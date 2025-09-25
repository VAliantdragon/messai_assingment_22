package com.hdfclife.store.micro1.product_micro1.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hdfclife.store.micro1.product_micro1.model.Order;
import com.hdfclife.store.micro1.product_micro1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public OrderService(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
        this.restTemplate = restTemplate;
    }

    public Optional<Order> createOrder(Order order) {
        return Optional.of(orderRepository.save(order));
    }

    public Optional<Order> getOrderWithPaymentStatus(String id, String clientType) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            String paymentStatus = getPaymentStatusFromService(id, clientType);
            order.setPaymentStatus(paymentStatus);
            return Optional.of(order);
        }
        return Optional.empty();
    }

    private String getPaymentStatusFromService(String orderId, String clientType) {
        try {
            switch (clientType.toLowerCase()) {
                case "resttemplate":
                    return getPaymentStatusWithRestTemplate(orderId);
                case "webclient":
                    return getPaymentStatusWithWebClient(orderId).block();
                // We've commented out Feign Client for now
                // case "feignclient":
                // return getPaymentStatusWithFeignClient(orderId);
                default:
                    return "NOT_FOUND";
            }
        } catch (Exception e) {
            System.err.println("Error fetching payment status: " + e.getMessage());
            return "NOT_FOUND";
        }
    }

    // Inter-service communication implementations
    private String getPaymentStatusWithRestTemplate(String orderId) {
        ResponseEntity<JsonNode> response = restTemplate.exchange(
                "http://localhost:8082/payments/" + orderId,
                HttpMethod.GET,
                null,
                JsonNode.class
        );
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().get("status").asText();
        }
        return "NOT_FOUND";
    }

    private Mono<String> getPaymentStatusWithWebClient(String orderId) {
        return webClient.get()
                .uri("/payments/" + orderId)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.get("status").asText())
                .defaultIfEmpty("NOT_FOUND");
    }

    // This method is no longer used, but kept for reference
    // private String getPaymentStatusWithFeignClient(String orderId) {
    //     return paymentClient.getPaymentStatus(orderId);
    // }
}
