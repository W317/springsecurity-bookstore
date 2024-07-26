package com.example.springboot_book_store.repository;

import com.example.springboot_book_store.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
