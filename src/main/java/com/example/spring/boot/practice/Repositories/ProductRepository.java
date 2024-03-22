package com.example.spring.boot.practice.Repositories;

import com.example.spring.boot.practice.Entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


    @Query(value = "select * from product_entity  where  product_name = :name", nativeQuery = true)
    public ProductEntity productFindByName(@Param("name") String name);

    @Query(value = "select * from product_entity where category_entity_id = :id", nativeQuery = true)
    public List<ProductEntity> getAllProductEntity(@Param("id") int id);
}
