package com.example.Ecommerce.controller;

import com.example.Ecommerce.Dto.CustomerOrderDto;

import com.example.Ecommerce.model.CustomerOrder;
import com.example.Ecommerce.service.CustomerOrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RequestMapping("/api/orders")
public class CustomerOrderController {

    private final CustomerOrderService orderService;

    @PostMapping(value = "/save")
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody @Valid CustomerOrderDto orderDto) {
        CustomerOrder savedOrder = orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
}
