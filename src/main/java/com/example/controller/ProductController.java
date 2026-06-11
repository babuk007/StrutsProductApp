package com.example.controller;

import com.example.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("/struts-book")
    public ProductDto getStrutsBookDetails() {
        // Original business logic adapted to return a DTO
        return new ProductDto("Struts Book", 49.99, "A complete guide to Struts framework.");
    }
}
