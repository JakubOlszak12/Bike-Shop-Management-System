package com.example.Ecommerce.repository;

import com.example.Ecommerce.Dto.ProductDto;
import com.example.Ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName")
    Page<Product> findProductsByCategoryName(@Param("categoryName") String categoryName, Pageable pageable);
}
