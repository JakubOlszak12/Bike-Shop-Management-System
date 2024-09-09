package com.example.Ecommerce.controller;


import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.Exception.ProductException;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

 
    @GetMapping("/productsPage")
    public ResponseEntity<Page<Product>> getProductsPage(@PageableDefault(size = 10)@SortDefault("createdAt")Pageable pageable) {
        Page<Product> productsPage = productService.getProductsPage(pageable);

        if (productsPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productsPage);
        }
    }

    @GetMapping("/productsDtoPage")
    public ResponseEntity<Page<ProductDto>> getProductsDtoPage(@PageableDefault(size = 10)@SortDefault("createdAt")Pageable pageable) {
        Page<ProductDto> productsPage = productService.getProductsDtoPage(pageable);

        if (productsPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productsPage);
        }
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductDto(@PathVariable Long id) {
        try {
            ProductDto product = productService.getProductDto(id);
            return ResponseEntity.ok().body(product);
        } catch (ProductException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getProductError().name(),ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.ok().body("Product successfully deleted");
        } catch (ProductException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getProductError().name(),ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/products/category")
    public ResponseEntity<Page<ProductDto>> getProductsByCategoryDtoPage(@RequestParam String category, @PageableDefault(size = 10)@SortDefault("createdAt")Pageable pageable) {
        try {
            System.out.println(category);
            Page<ProductDto> productsPage = productService.findProductsByCategoryName(pageable, category);
            return ResponseEntity.ok().body(productsPage);
        } catch (ProductException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getProductError().name(),ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }
}