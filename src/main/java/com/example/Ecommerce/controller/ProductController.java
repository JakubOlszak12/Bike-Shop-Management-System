package com.example.Ecommerce.controller;

import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() throws IllegalAccessException {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable long id) throws IllegalAccessException {
        return productService.getProduct(id);
    }
}
