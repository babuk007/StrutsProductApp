package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiController {

    // In a larger application, this would typically be a separate file 
    // in a 'com.example.model' or 'com.example.domain' package.
    // Defined as a public record for modern, immutable data encapsulation.
    public record Product(String name, double price, String description) { }

    /**
     * Handles GET requests to /api/product to retrieve product details.
     * This method translates the business logic from the original Struts execute() method.
     * It hardcodes product information, which in a real application would be fetched from a service layer or database.
     *
     * @return A Product record containing the details, which Spring Boot will automatically serialize to JSON.
     */
    @GetMapping("/api/product")
    public Product getProduct() {
        // Simulating the business logic from the original Struts execute() method
        // Hardcoded product info (can later be fetched from a Service/DB)
        String name = "Spring Boot Book";
        double price = 59.99;
        String description = "A complete guide to Spring Boot framework.";
        
        return new Product(name, price, description);
    }
}
