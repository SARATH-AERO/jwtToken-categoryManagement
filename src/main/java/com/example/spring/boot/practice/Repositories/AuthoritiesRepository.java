package com.example.spring.boot.practice.Repositories;

import com.example.spring.boot.practice.Entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {

}