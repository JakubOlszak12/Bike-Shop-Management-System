package com.example.Ecommerce.Dto;

import com.example.Ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private int productionYear;
    private String fork;
    private String forkMaterial;
    private String frameMaterial;
    private String drive;
    private String frontDerailleur;
    private String rearDerailleur;
    private String handle;
    private String crank;
    private String cassette;
    private String brakeType;
    private String brake;
    private String wheel;
    private String wheelSize;
    private String tire;
    private String pedals;
    private String saddle;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String brandName;
    private String sizeName;
    private String categoryName;
}
