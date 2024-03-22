package com.example.spring.boot.practice.Service;

import com.example.spring.boot.practice.DTOs.ProductDTO;
import com.example.spring.boot.practice.Entity.CategoryEntity;
import com.example.spring.boot.practice.Entity.ProductEntity;
import com.example.spring.boot.practice.Repositories.CategoryRepository;
import com.example.spring.boot.practice.Repositories.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository CategoryRepository;


    @Autowired
    ProductRepository productRepository;

    public String addCategory(String name){
        if( CategoryRepository.categoryFindByName(name) != null)
            return  name + " category already exists";
        CategoryEntity category = new CategoryEntity();
        category.setCategoryName(name);
        CategoryRepository.save(category);

        return name +" category added successfully";
    }

    public String getCategory(String name){

        CategoryEntity category = CategoryRepository.categoryFindByName(name);
        if(category == null)
            return "category not exists";
        else
            return  category.getCategoryName() + "exists in the database";

    }

    public String removeCategory(String name){
        CategoryEntity category = CategoryRepository.categoryFindByName(name);
        if( category == null )
            return name+ " category not exists";
        else{
            CategoryRepository.delete(category);
            return name+ " category deleted successfully";
        }
    }

    public String changeCategoryName(String oldName, String newName){

        CategoryEntity category = CategoryRepository.categoryFindByName(oldName);
        if( category == null )
            return oldName+ " category not exists";
        else {
            category.setCategoryName(newName);
            CategoryRepository.save(category);
            return oldName+" category name updated successfully as "+newName;
        }
    }

    public List<ProductDTO> getAllProductsUnderTheCategory(String categoryName){

            CategoryEntity category = CategoryRepository.categoryFindByName(categoryName);

        List<ProductDTO> list = new ArrayList<>();


            if(category == null)
                return list;
            else {

                List<ProductEntity> listProducts = productRepository.getAllProductEntity(category.getId());


                for (ProductEntity productEntity : listProducts) {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductName(productEntity.getProductName());
                    productDTO.setProductCategoryName(productEntity.getCategoryEntity().getCategoryName());
                    productDTO.setPrice(productEntity.getPrice());
                    productDTO.setQuantity(productEntity.getQuantity());
                    productDTO.setBrand(productEntity.getBrand());
                    productDTO.setDescription(productEntity.getDescription());
                    productDTO.setColor(productEntity.getColor());

                    list.add(productDTO);
                }

                return list;

            }

        }

        public List<String> getAllCategory(){
            List<CategoryEntity> categoryEntities = CategoryRepository.findAll();

            List<String> list = new ArrayList<>();

            for(CategoryEntity category : categoryEntities)
                list.add(category.getCategoryName());

            return list;
        }

}


















