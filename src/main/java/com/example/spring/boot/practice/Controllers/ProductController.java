package com.example.spring.boot.practice.Controllers;


import com.example.spring.boot.practice.DTOs.ProductDTO;
import com.example.spring.boot.practice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        String response = productService.addProduct(productDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/getProduct/{name}")
    public  ResponseEntity<Object> getProduct(@PathVariable String name){
        Object response = productService.getProductDetails(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO){
        String response = productService.updateProduct(productDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{name}")
    public ResponseEntity<String> deleteProduct(@PathVariable String name){
        String response = productService.deleteProduct(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<Object> getAllProducts(){
        Object list = productService.getAllProducts();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
