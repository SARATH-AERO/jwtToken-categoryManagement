package com.example.spring.boot.practice.Repositories;

import com.example.spring.boot.practice.Entity.CategoryEntity;
import com.example.spring.boot.practice.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {


    @Query (value = "select * from category_entity  where category_name = :name",nativeQuery = true)
    public CategoryEntity categoryFindByName(@Param("name") String name);


}
