package com.example.Ecommerce.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private Long orderId;
}
