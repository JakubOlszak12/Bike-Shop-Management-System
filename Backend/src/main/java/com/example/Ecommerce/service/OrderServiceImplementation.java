package com.example.Ecommerce.service;

import com.example.Ecommerce.Exception.OrderError;
import com.example.Ecommerce.Exception.OrderException;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.Dto.OrderDto;
import com.example.Ecommerce.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService{
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final PaymentMethodRepository paymentMethodRepository;
    private final DeliveryMethodRepository deliveryMethodRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
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
    public Order saveOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setId(orderDto.getId());
        order.setStreet(orderDto.getStreet());
        order.setPostalCode(orderDto.getPostalCode());
        order.setCity(orderDto.getCity());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setPrice(orderDto.getPrice());
        order.setCreated_at(orderDto.getCreated_at());
        if (orderDto.getPaymentMethodId() != null) {
            PaymentMethod paymentMethod = paymentMethodRepository.findById(orderDto.getPaymentMethodId())
                    .orElseThrow(() -> new RuntimeException("Payment method not found"));
            order.setPaymentMethod(paymentMethod);
        }

        if (orderDto.getDeliveryMethodId() != null) {
            DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(orderDto.getDeliveryMethodId())
                    .orElseThrow(() -> new RuntimeException("Delivery method not found"));
            order.setDeliveryMethod(deliveryMethod);
        }

        if (orderDto.getStatusId() != null) {
            Status status = statusRepository.findById(orderDto.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            order.setStatus(status);
        }

        if (orderDto.getUserId() != null) {
            User user = userRepository.findById(orderDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            order.setUser(user);
        }
        return orderRepository.save(order);
    }

    @Override
    public OrderDto getOrder(Long id) {
        Order order =  orderRepository.findById(id).
                orElseThrow(() -> new OrderException(OrderError.ORDER_NOT_FOUND));
        return modelMapper.map(order, OrderDto.class);
    }
}
