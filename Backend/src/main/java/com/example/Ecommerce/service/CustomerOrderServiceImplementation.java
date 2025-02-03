package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.CustomerOrderDetailDto;
import com.example.Ecommerce.Dto.CustomerOrderDto;
import com.example.Ecommerce.Exception.CustomerOrderError;
import com.example.Ecommerce.Exception.CustomerOrderException;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerOrderServiceImplementation implements CustomerOrderService{
    private final CustomerOrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final PaymentMethodRepository paymentMethodRepository;
    private final DeliveryMethodRepository deliveryMethodRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Override
    public List<CustomerOrder> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<CustomerOrderDto> getOrdersDtoPage(Pageable pageable) {
        Page<CustomerOrder> orderPage = orderRepository.findAll(pageable);
        return orderPage.map((element) -> modelMapper.map(element, CustomerOrderDto.class));
    }

    @Override
    public CustomerOrder saveOrder(CustomerOrderDto orderDto) {
        CustomerOrder order = new CustomerOrder();

        order.setStreet(orderDto.getStreet());
        order.setPostalCode(orderDto.getPostalCode());
        order.setCity(orderDto.getCity());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setPrice(orderDto.getPrice());
        order.setCreated_at(LocalDateTime.now());

        PaymentMethod paymentMethod = paymentMethodRepository.findById(orderDto.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Payment method not found"));
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(orderDto.getDeliveryMethodId())
                .orElseThrow(() -> new RuntimeException("Delivery method not found"));
        Status status = statusRepository.findById(orderDto.getStatusId())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setPaymentMethod(paymentMethod);
        order.setDeliveryMethod(deliveryMethod);
        order.setStatus(status);
        order.setUser(user);

        List<CustomerOrderDetail> customerOrderDetails = new ArrayList<>();
        double price = 0;
        for (CustomerOrderDetailDto detailDto : orderDto.getOrderDetails()) {
            CustomerOrderDetail detail = new CustomerOrderDetail();
            detail.setCustomerOrder(order);
            Product product = productRepository.findById(detailDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if(product.getQuantity() == 0){
                throw new RuntimeException("There is no more stock of this product");
            }else if(product.getQuantity() < detailDto.getQuantity()){
                throw new RuntimeException("There is only " + product.getQuantity() + " stock of this product");
            }else{
                product.setQuantity(product.getQuantity() - detailDto.getQuantity());
                productRepository.save(product);
            }
            detail.setProduct(product);
            detail.setQuantity(detailDto.getQuantity());
            detail.setUnitPrice(product.getPrice());
            detail.setTotalPrice(detail.getUnitPrice() * detail.getQuantity());
            price += detail.getTotalPrice();
            customerOrderDetails.add(detail);
        }
        order.setPrice(price);
        order.setCustomerOrderDetailsList(customerOrderDetails);
        return orderRepository.save(order);
    }

    @Override
    public CustomerOrderDto getOrder(Long id) {
        CustomerOrder order =  orderRepository.findById(id).
                orElseThrow(() -> new CustomerOrderException(CustomerOrderError.ORDER_NOT_FOUND));
        return modelMapper.map(order, CustomerOrderDto.class);
    }
}
