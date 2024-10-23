package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.OrderDto;
import com.example.Ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<Order> getOrders100();
    List<Order> getOrders100000();

    Order createOrder(Order order);

    Page<Order> getOrdersPage(Pageable pageable);

    Order saveOrder(OrderDto order);

    OrderDto getOrder(Long id);

    void deleteOrder100();

    void deleteOrder100000();

    public List<Order> getLastOrders(int count);

    void deleteOrder(Order order);

    void updateOrders100();

    void updateOrders100000();

    // AVG price for first 100 records
    double calculateAvgPriceForFirst100();

    // AVG price for first 100,000 records
   double calculateAvgPriceForFirst100000();

    // COUNT for first 100 records
    long calculateCountForFirst100();

    // COUNT for first 100,000 records
    long calculateCountForFirst100000();

    // SUM price for first 100 records
    double calculateSumPriceForFirst100();

    // SUM price for first 100,000 records
    double calculateSumPriceForFirst100000();

    List<Order>findOrdersByProductId(Long productId, int limit);
}
