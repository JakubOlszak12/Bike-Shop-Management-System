package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Product;

import java.util.List;


public interface ProductService {

    List<Product> getProducts();
    Product getProduct(Long id);

}
