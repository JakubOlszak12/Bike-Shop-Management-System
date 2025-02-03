package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.CustomerOrderDetailDto;

import com.example.Ecommerce.Exception.CustomerOrderError;
import com.example.Ecommerce.Exception.CustomerOrderException;

import com.example.Ecommerce.model.CustomerOrder;
import java.util.Optional;
import com.example.Ecommerce.model.CustomerOrderDetail;
import com.example.Ecommerce.repository.CustomerOrderDetailRepository;
import com.example.Ecommerce.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderDetailsServiceImplementation implements CustomerOrderDetailsService {

    private final CustomerOrderRepository orderRepository;
    private final CustomerOrderDetailRepository orderDetailRepository;

    public CustomerOrderDetailsServiceImplementation(CustomerOrderRepository orderRepository,
                                                     CustomerOrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public CustomerOrderDetail save(CustomerOrderDetailDto orderDetail) {
        CustomerOrder order =  orderRepository.findById(orderDetail.getCustomerOrderId())
                .orElseThrow(() -> new CustomerOrderException(CustomerOrderError.ORDER_NOT_FOUND));
        CustomerOrderDetail customerOrderDetail1 = new CustomerOrderDetail();
        customerOrderDetail1.setCustomerOrder(order);
        customerOrderDetail1.setQuantity(orderDetail.getQuantity());
        customerOrderDetail1.setUnitPrice(orderDetail.getUnitPrice());
        customerOrderDetail1.setTotalPrice(orderDetail.getTotalPrice());
        return orderDetailRepository.save(customerOrderDetail1);
    }

    @Override
    public List<CustomerOrderDetail> findAllByCustomerOrderId(Long customerOrderId) {
        return orderDetailRepository.findAllByCustomerOrderId(customerOrderId);
    }
}
