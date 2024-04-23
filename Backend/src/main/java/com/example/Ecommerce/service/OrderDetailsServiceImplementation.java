package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.OrderDetailDto;
import com.example.Ecommerce.Exception.OrderError;
import com.example.Ecommerce.Exception.OrderException;
import com.example.Ecommerce.model.Order;
import com.example.Ecommerce.model.OrderDetail;
import com.example.Ecommerce.repository.OrderDetailRepository;
import com.example.Ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImplementation implements OrderDetailsService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailsServiceImplementation(OrderRepository orderRepository,
                                             OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetail save(OrderDetailDto orderDetail) {
        Order order = orderRepository.findById(orderDetail.getOrderId())
                .orElseThrow(() -> new OrderException(OrderError.ORDER_NOT_FOUND));
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setOrder(order);
        orderDetail1.setQuantity(orderDetail.getQuantity());
        orderDetail1.setUnitPrice(orderDetail.getUnitPrice());
        orderDetail1.setTotalPrice(orderDetail.getTotalPrice());
        return orderDetailRepository.save(orderDetail1);
    }

    @Override
    public List<OrderDetail> findAllByOrderId(Long orderId) {
        return orderDetailRepository.findAllByOrderId(orderId);
    }
}
