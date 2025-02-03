package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.CustomerOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderDetailRepository extends JpaRepository<CustomerOrderDetail,Long> {

    List<CustomerOrderDetail> findAllByCustomerOrderId(Long customerOrderId);
}
