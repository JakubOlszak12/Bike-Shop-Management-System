package com.example.Ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;


@Entity
@Getter
@Setter
public class Product {
    @Id
    private long id;
    private String name;
    private String description;
    private double price;
}
