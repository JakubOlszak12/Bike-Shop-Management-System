package com.example.Ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private int quantity;
    @NotNull
    private int productionYear;
    @NotNull
    private String fork;
    @NotNull
    private String forkMaterial;
    @NotNull
    private String frameMaterial;
    @NotNull
    private String drive;
    @NotNull
    private String frontDerailleur;
    @NotNull
    private String rearDerailleur;
    @NotNull
    private String handle;
    @NotNull
    private String crank;
    @NotNull
    private String cassette;
    @NotNull
    private String brakeType;
    @NotNull
    private String brake;
    @NotNull
    private String wheel;
    @NotNull
    private String wheelSize;
    @NotNull
    private String tire;
    @NotNull
    private String pedals;
    @NotNull
    private String saddle;
    @NotNull
    private double price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id",nullable = false)
    private Brand brand;
}
