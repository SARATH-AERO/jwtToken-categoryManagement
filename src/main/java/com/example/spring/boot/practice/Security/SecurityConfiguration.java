package com.example.spring.boot.practice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return  httpSecurity.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/authenticate").permitAll()
//  permit all to create jwt token & return
                .requestMatchers("category/**").hasRole("ADMIN")
// permit only has role of ADMIN
                .anyRequest().authenticated()
                .and()
//  adding JWT filter before giving access by checking role
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

//encoder is must. this encoding algo will be used by spring securit to check password
//    if pass not matching even giving right pass. the encode algo you used to save password in the database
//    and the encode algo you use here is not same
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Or any other encoder you are using
    }


//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails sarath = User.builder().
//                username("sarath").
//                password("{noop}sarath").
//                roles("Admin").
//                build();
//
//        UserDetails rashmika = User.builder().
//                username("rashmika").
//                password("{noop}rashmika").
//                roles("Owner").
//                build();
//
//        UserDetails chirji = User.builder().
//                username("chirji").
//                password("{noop}chirji").
//                roles("Customer").
//                build();
//
//
//        return new InMemoryUserDetailsManager(sarath,rashmika,chirji);
//
//    }

//    @Bean
//    public UserDetailsManager userDetailManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcDetails = new  JdbcUserDetailsManager(dataSource);
//
//        jdbcDetails.setUsersByUsernameQuery(
//                "select usern, passw, enabled from myusers where usern=?");
//
//        jdbcDetails.setAuthoritiesByUsernameQuery(
//                "select user, myauthority from myauthorities where user=?");
//
//        return jdbcDetails;
//    }





}
