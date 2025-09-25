package com.hdfclife.store.product.store.controller;

import com.hdfclife.store.product.store.model.Product;
import com.hdfclife.store.product.store.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory/products")
public class ProductController {

    @Autowired
    private InventoryService inventoryService;

    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    // GET a single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) {
        return inventoryService.getProductById(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return inventoryService.createProduct(product);
    }

    // UPDATE an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
                                                 @RequestBody Product productDetails) {
        return inventoryService.updateProduct(productId, productDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to decrement stock, called by the order service
    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> decrementProductStock(@PathVariable(value = "id") Long productId, @RequestParam int quantity) {
        return inventoryService.decrementStock(productId, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build()); // Return bad request if stock is insufficient
    }

    // DELETE a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Long productId) {
        if (inventoryService.deleteProduct(productId)) {
            return ResponseEntity.ok().<Void>build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
