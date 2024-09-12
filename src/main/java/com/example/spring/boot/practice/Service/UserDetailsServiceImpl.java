package com.example.spring.boot.practice.Service;

import com.example.spring.boot.practice.Entity.Authorities;
import com.example.spring.boot.practice.Entity.Users;
import com.example.spring.boot.practice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users user = userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException(username +" user not found"));

        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        for(Authorities authority : user.getAuthorities()){
            authoritySet.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authoritySet
        );

    }
}
