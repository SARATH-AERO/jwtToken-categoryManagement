package com.example.spring.boot.practice.Controllers;

import com.example.spring.boot.practice.DTOs.ProductDTO;
import com.example.spring.boot.practice.Service.CategoryService;
import com.example.spring.boot.practice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/addCategory/{name}")
    public ResponseEntity<String> addCategory(@PathVariable String name){
        String response = categoryService.addCategory(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @GetMapping("/hio/{ji}")
//    public ResponseEntity<String>  getCategory(@PathVariable String ji){
//
//        return new ResponseEntity<>(ji+"   controllers works", HttpStatus.OK);
//    }



    @GetMapping("/categoryCheck/{name}")
    public ResponseEntity<String> categoryCheck(@PathVariable String name) {
        String response = categoryService.getCategory(name);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PutMapping("/categoryNameChange")
    public ResponseEntity<String> categoryCheck(@RequestParam String oldName, @RequestParam String newName) {
        String response = categoryService.changeCategoryName(oldName, newName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam String name){
        String response = categoryService.removeCategory(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





    @GetMapping("/getAllProductUnderCategory/{name}")
    public ResponseEntity<Object> getAllProductUnderCategory(@PathVariable String name){
        try {
            List<ProductDTO> response = categoryService.getAllProductsUnderTheCategory(name);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<String>> getAllCategory(){
        List<String> ans = categoryService.getAllCategory();
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }



}
