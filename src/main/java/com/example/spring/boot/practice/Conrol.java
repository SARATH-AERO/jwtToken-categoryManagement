package com.example.spring.boot.practice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Conrol {


    @GetMapping("/tes")
    public String tru(){
        return "hi this works";
    }
}
