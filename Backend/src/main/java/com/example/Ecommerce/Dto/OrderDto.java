package com.example.Ecommerce.Dto;

import com.example.Ecommerce.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Order}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto{
    Long id;
    String street;
    String postalCode;
    String city;
    String phoneNumber;
    String email;
    double price;
    LocalDateTime created_at;
    Long invoiceId;
    Long paymentMethodId;
    String paymentMethodName;
    Long deliveryMethodId;
    String deliveryMethodName;
    Long statusId;
    String statusName;
    Long userId;
}