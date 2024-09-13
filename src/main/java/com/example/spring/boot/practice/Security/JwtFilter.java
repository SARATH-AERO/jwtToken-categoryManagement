package com.example.spring.boot.practice.Security;

import com.example.spring.boot.practice.Service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
// token is passed in header. we receive and check.
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwtToken = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwtToken);
        }

//The Authentication object within the SecurityContext holds the details of the current user's authentication status.
//When a user is authenticated, this object contains information such as:
//Principal (e.g., UserDetails object with user information like username).
//Authorities (roles or permissions assigned to the user).
//Credentials (typically null, as credentials are validated only during authentication).
//isAuthenticated (a boolean flag indicating whether the user is authenticated).
//If the authentication object is null or not.
        if( username != null && SecurityContextHolder.getContext().getAuthentication() == null){

//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//  jwt principle. not interact with database for token&user&roles validation. so userdetails not fetched.
//  only used for frequently changing roles.

//after token validation
            if(jwtUtil.validateToken(jwtToken, username)){

                List<String> roles = jwtUtil.extractRolesFromToken(jwtToken);
                Collection<? extends GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
//  SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
                        .collect(Collectors.toList());
//  Collectors.toList() converts the stream into a List.

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails
// username fetched from token not database
                        username
                        ,null
//                        ,userDetails.getAuthorities()
//now roles are fetched from token not database
                        ,authorities
                );
// we set above mentioned details of UserDetails with roles.
//  you extract roles from it and set them in the Authentication object:
//  When a request is processed, Spring Security uses the Authentication object
//  in the SecurityContext to enforce access control.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
