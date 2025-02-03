package com.example.Ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int quantity;

    @NotNull
    private double unitPrice;

    @NotNull
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "customerOrder_id", nullable = false)
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
