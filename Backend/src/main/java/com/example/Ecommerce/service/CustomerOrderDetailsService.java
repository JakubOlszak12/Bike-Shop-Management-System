package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.CustomerOrderDetailDto;
import com.example.Ecommerce.model.CustomerOrderDetail;

import java.util.List;


public interface CustomerOrderDetailsService {
    CustomerOrderDetail save(CustomerOrderDetailDto orderDetail);
    List<CustomerOrderDetail> findAllByCustomerOrderId(Long customerOrderId);
}
