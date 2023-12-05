package com.example.Ecommerce.service;

import com.example.Ecommerce.Exception.OrderError;
import com.example.Ecommerce.Exception.OrderException;
import com.example.Ecommerce.model.Order;
import com.example.Ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getOrdersPage(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    // TODO: 05.12.2023  
    @Override
    public Order saveOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).
                orElseThrow(() -> new OrderException(OrderError.ORDER_NOT_FOUND));
    }
}
