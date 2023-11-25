package com.example.Ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;


@Entity
@Getter
@Setter

@NoArgsConstructor
public class Product {
    @Id
    private long id;
    private String name;
    private String description;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    private double price;
}
