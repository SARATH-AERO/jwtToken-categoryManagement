package com.example.spring.boot.practice.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;

    private double price;

    private String color;

    private int quantity;

    private String brand;

    private String description;

    @ManyToOne
    @JoinColumn
    private CategoryEntity categoryEntity;


}
