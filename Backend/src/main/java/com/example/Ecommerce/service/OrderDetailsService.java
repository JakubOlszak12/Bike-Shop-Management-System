package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.OrderDetailDto;
import com.example.Ecommerce.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderDetailsService {
    OrderDetail save(OrderDetailDto orderDetail);
    List<OrderDetail> findAllByOrderId(Long orderId);
}
