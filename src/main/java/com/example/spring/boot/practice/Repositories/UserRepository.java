package com.example.spring.boot.practice.Repositories;

import com.example.spring.boot.practice.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String > {
}



