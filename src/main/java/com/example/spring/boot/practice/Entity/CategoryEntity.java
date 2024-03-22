package com.example.spring.boot.practice.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categoryName;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();

}
