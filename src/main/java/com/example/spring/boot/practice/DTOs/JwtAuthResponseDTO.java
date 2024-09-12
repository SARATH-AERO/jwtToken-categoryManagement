package com.example.spring.boot.practice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
public class JwtAuthResponseDTO {

    private String accessToken;
    private String tokenTpe = "Bearer";
}
