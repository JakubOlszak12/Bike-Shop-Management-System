package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;

import java.util.List;


public interface ProductService {

    List<Product> getProducts();
    Product getProduct(Long id);

    Page<Product> getProductsPage(Pageable pageable);

}
