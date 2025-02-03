package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.CustomerOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {

    List<CustomerOrder> findAllByUserId(Long userId);
}
