package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    Page<Order> getOrdersPage(Pageable pageable);

    Order saveOrder(Order order);

    Order getOrder(Long id);



}
