package com.hdfclife.store.product.store.service;

import com.hdfclife.store.product.store.model.Product;
import com.hdfclife.store.product.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    // Retrieves a list of all products in the inventory
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieves a single product by its ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Creates a new product and saves it to the inventory
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Updates an existing product's details
    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    product.setDescription(productDetails.getDescription());
                    product.setStock(productDetails.getStock()); // Update stock
                    return productRepository.save(product);
                });
    }

    // Decrement product stock when an order is created
    public Optional<Product> decrementStock(Long id, int quantity) {
        return productRepository.findById(id)
                .map(product -> {
                    if (product.getStock() >= quantity) {
                        product.setStock(product.getStock() - quantity);
                        return productRepository.save(product);
                    }
                    return null; // Return null if stock is insufficient
                });
    }

    // Deletes a product from the inventory
    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                }).orElse(false);
    }
}
