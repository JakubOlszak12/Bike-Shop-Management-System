package com.example.Ecommerce.Dto;

import com.example.Ecommerce.model.CustomerOrder;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * DTO for {@link CustomerOrder}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDto{
    Long id;
    String street;
    String postalCode;
    String city;
    String phoneNumber;
    double price;
    LocalDateTime created_at;
    Long invoiceId;
    Long paymentMethodId;
    Long deliveryMethodId;
    Long statusId;
    Long userId;
    ArrayList<CustomerOrderDetailDto> orderDetails;
}