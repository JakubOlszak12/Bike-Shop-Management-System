package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUserId(Long userId);

 List<Order> findTop100ByOrderByIdAsc();
 List<Order> findTop100000ByOrderByIdAsc();

 void deleteTop100000ByOrderByIdDesc();

 void deleteTop100ByOrderByIdDesc();

 List<Order> findTopByOrderByIdDesc(PageRequest pageRequest);

 List<Order> findTop100ByOrderByIdDesc();
 List<Order> findTop100000ByOrderByIdDesc();

 List<Order> findFirst100ByOrderByIdAsc();

 List<Order> findFirst100000ByOrderByIdAsc();

 // AVG price for first 100 records
 @Query("SELECT AVG(o.price) FROM Order o WHERE o.id < 101 ORDER BY o.id ASC")
 double calculateAvgPriceForFirst100();

 // AVG price for first 100,000 records
 @Query("SELECT AVG(o.price) FROM Order o WHERE o.id < 100001 ORDER BY o.id ASC")
 double calculateAvgPriceForFirst100000();

 // COUNT for first 100 records
 @Query("SELECT COUNT(o) FROM Order o WHERE o.id < 101 ORDER BY o.id ASC")
 long calculateCountForFirst100();

 // COUNT for first 100,000 records
 @Query("SELECT COUNT(o) FROM Order o WHERE o.id < 100001 ORDER BY o.id ASC")
 long calculateCountForFirst100000();

 // SUM price for first 100 records
 @Query("SELECT SUM(o.price) FROM Order o WHERE o.id < 101 ORDER BY o.id ASC")
 double calculateSumPriceForFirst100();

 // SUM price for first 100,000 records
 @Query("SELECT SUM(o.price) FROM Order o WHERE o.id < 100001 ORDER BY o.id ASC")
 double calculateSumPriceForFirst100000();

 // Query for joining Order and OrderDetails to fetch Orders where product_id = :productId
// @Query(value = "SELECT * FROM `order` o JOIN order_detail od ON o.id = od.order_id WHERE od.product_id = :productId ORDER BY o.id ASC LIMIT 100", nativeQuery = true)
// List<Order> findOrdersByProductIdWithLimit100(@Param("productId") Long productId);
 @Query(value = "SELECT o.* FROM `order` o JOIN order_detail od ON o.id = od.order_id " +
         "WHERE od.product_id = :productId ORDER BY o.id ASC LIMIT 100", nativeQuery = true)
 List<Order> findOrdersByProductIdWithLimit100(@Param("productId") Long productId);

 // Native query to find orders by product ID with a limit of 100,000
 @Query(value = "SELECT o.* FROM `order` o JOIN order_detail od ON o.id = od.order_id WHERE od.product_id = :productId ORDER BY o.id ASC LIMIT 100000", nativeQuery = true)
 List<Order> findOrdersByProductIdWithLimit100000(@Param("productId") Long productId);
}
