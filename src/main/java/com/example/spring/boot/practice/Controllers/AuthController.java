package com.example.spring.boot.practice.Controllers;

import com.example.spring.boot.practice.DTOs.JwtAuthResponseDTO;
import com.example.spring.boot.practice.Security.JwtUtil;
import com.example.spring.boot.practice.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestParam String username, @RequestParam String password) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }catch (BadCredentialsException e){
            throw  new Exception("Invalid username or password", e);
        }
// userdetails only fetched to provide token not verifying it.
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwtToken);
    }
}
