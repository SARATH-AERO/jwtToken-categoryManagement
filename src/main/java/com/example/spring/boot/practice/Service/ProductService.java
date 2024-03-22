package com.example.spring.boot.practice.Service;

import com.example.spring.boot.practice.DTOs.ProductDTO;
import com.example.spring.boot.practice.Entity.CategoryEntity;
import com.example.spring.boot.practice.Entity.ProductEntity;
import com.example.spring.boot.practice.Repositories.CategoryRepository;
import com.example.spring.boot.practice.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public String addProduct(ProductDTO productDTO){
        ProductEntity product = productRepository.productFindByName(productDTO.getProductName());
        CategoryEntity category = categoryRepository.categoryFindByName(productDTO.getProductCategoryName());
        if( category == null )
            return productDTO.getProductCategoryName()+ " category not exists";
        else if( product != null )
            return productDTO.getProductCategoryName()+ " category exists but " +productDTO.getProductName()+ " product already exists";
        else{
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductName(productDTO.getProductName());
            productEntity.setCategoryEntity(category);
            productEntity.setBrand(productDTO.getBrand());
            productEntity.setPrice(productDTO.getPrice());
            productEntity.setQuantity(productDTO.getQuantity());
            productEntity.setDescription(productDTO.getDescription());
            productEntity.setBrand(productDTO.getBrand());

            productRepository.save(productEntity);

            return productDTO.getProductName()+" product added successfully";
        }
    }

    public String updateProduct(ProductDTO productDTO){
        ProductEntity productEntity = productRepository.productFindByName(productDTO.getProductName());
        CategoryEntity category = categoryRepository.categoryFindByName(productDTO.getProductCategoryName());
        if( category == null )
            return productDTO.getProductCategoryName()+ " category not exists";
        else if( productEntity == null )
            return productDTO.getProductCategoryName()+ " category exists but " +productDTO.getProductName()+ " product not exists";
        else{
            productEntity.setProductName(productDTO.getProductName());
            productEntity.setCategoryEntity(category);
            productEntity.setBrand(productDTO.getBrand());
            productEntity.setPrice(productDTO.getPrice());
            productEntity.setQuantity(productDTO.getQuantity());
            productEntity.setDescription(productDTO.getDescription());
            productEntity.setBrand(productDTO.getBrand());

            productRepository.save(productEntity);

            return productDTO.getProductName()+" product updated successfully";
        }
    }


    public String deleteProduct(String name){
        ProductEntity productEntity = productRepository.productFindByName(name);
        if( productEntity == null )
            return name+ " product does not exists";
        else{
            CategoryEntity categoryEntity =   productEntity.getCategoryEntity();
            categoryEntity.getProductEntityList().remove(productEntity);
            productRepository.delete(productEntity);
            return name+ " product deleted successfully";
        }
    }

    public Object getProductDetails(String name) {
        ProductEntity productEntity = productRepository.productFindByName(name);
        if (productEntity == null)
            return name + " product does not exists";
        else {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(productEntity.getProductName());
            productDTO.setProductCategoryName(productEntity.getCategoryEntity().getCategoryName());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setQuantity(productEntity.getQuantity());
            productDTO.setBrand(productEntity.getBrand());
            productDTO.setDescription(productEntity.getDescription());
            productDTO.setColor(productEntity.getColor());
            return productDTO;
        }

    }


    public Object getAllProducts(){
        List<ProductEntity> listProducts = productRepository.findAll();
        List<Object> list = new ArrayList<>();

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
