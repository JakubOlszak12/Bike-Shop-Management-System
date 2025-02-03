package com.example.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double tax;

    @NotNull
    private double value;

    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime paymentDate;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
