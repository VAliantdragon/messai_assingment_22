package com.hdfclife.store.micro1.product_micro1.model;

import com.hdfclife.store.micro1.product_micro1.model.Order;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;


@Document(collection ="orders")
public class Order {
    @Id
    private String id;
    private String productName;
    private int quantity;

    @Transient
    private String paymentStatus;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
