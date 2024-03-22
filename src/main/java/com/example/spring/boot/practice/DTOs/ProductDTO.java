package com.example.spring.boot.practice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String productName;

    private String productCategoryName;

    private double price;

    private String color;

    private int quantity;

    private String brand;

    private String description;

}
