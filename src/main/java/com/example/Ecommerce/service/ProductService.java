package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}
