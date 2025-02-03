package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.CustomerOrderDto;
import com.example.Ecommerce.model.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerOrderService {

    List<CustomerOrder> getOrders();

    Page<CustomerOrderDto> getOrdersDtoPage(Pageable pageable);

    CustomerOrder saveOrder(CustomerOrderDto order);

    CustomerOrderDto getOrder(Long id);



}
