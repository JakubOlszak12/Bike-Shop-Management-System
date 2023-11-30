package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {

    List<Product> getProducts();
    Product getProduct(Long id);

    Page<Product> getProductsPage(Pageable pageable);

}
