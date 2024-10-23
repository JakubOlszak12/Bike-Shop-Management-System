package com.example.Ecommerce.service;

import com.example.Ecommerce.Exception.OrderError;
import com.example.Ecommerce.Exception.OrderException;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.Dto.OrderDto;
import com.example.Ecommerce.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Order> getOrders100() {
        List<Order> orders = new ArrayList<>();
        for(long i = 1; i < 101; i++) {
            Order order = orderRepository.findById(i).get();
            orders.add(order);
        }
        return orders;
    }

    @Override
    public List<Order> getOrders100000() {
        List<Order> orders = new ArrayList<>();
        for(long i = 1; i < 100001; i++) {
            Order order = orderRepository.findById(i).get();
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
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

    @Override
    public void deleteOrder100() {
        List<Order> orders = orderRepository.findTop100ByOrderByIdDesc();
        for (Order order : orders) {
            orderRepository.delete(order);
        }
    }

    @Override
    public void deleteOrder100000() {
        List<Order> orders = orderRepository.findTop100000ByOrderByIdDesc();
        for (Order order : orders) {
            orderRepository.delete(order);
        }
    }

    @Override
    public List<Order> getLastOrders(int count) {
        return orderRepository.findTopByOrderByIdDesc(PageRequest.of(0, count));
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
    @Override
    public void updateOrders100() {
        List<Order> orders = orderRepository.findFirst100ByOrderByIdAsc();
        orders.forEach(order -> {
            order.setPrice(order.getPrice() * 0.99);  // Reduce price by 1%
            orderRepository.save(order);  // Save the updated order
        });
    }
    @Override
    public void updateOrders100000() {
        List<Order> orders = orderRepository.findFirst100000ByOrderByIdAsc();
        orders.forEach(order -> {
            order.setPrice(order.getPrice() * 0.99);  // Reduce price by 1%
            orderRepository.save(order);  // Save the updated order
        });
    }

    // AVG price for first 100 records
    public double calculateAvgPriceForFirst100() {
        return orderRepository.calculateAvgPriceForFirst100();
    }

    // AVG price for first 100,000 records
    public double calculateAvgPriceForFirst100000() {
        return orderRepository.calculateAvgPriceForFirst100000();
    }

    // COUNT for first 100 records
    public long calculateCountForFirst100() {
        return orderRepository.calculateCountForFirst100();
    }

    // COUNT for first 100,000 records
    public long calculateCountForFirst100000() {
        return orderRepository.calculateCountForFirst100000();
    }

    // SUM price for first 100 records
    public double calculateSumPriceForFirst100() {
        return orderRepository.calculateSumPriceForFirst100();
    }

    // SUM price for first 100,000 records
    public double calculateSumPriceForFirst100000() {
        return orderRepository.calculateSumPriceForFirst100000();
    }

    @Override
    public List<Order> findOrdersByProductId(Long productId, int limit) {
        if(limit == 100){
            return orderRepository.findOrdersByProductIdWithLimit100(productId);
        }else if(limit == 100000){
            return orderRepository.findOrdersByProductIdWithLimit100000(productId);
        }
        return null;
    }
}
