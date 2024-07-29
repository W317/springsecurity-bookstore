package com.example.springboot_book_store.repository;

import com.example.springboot_book_store.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Borrower, Integer> {
    Borrower findByUsername(String username);
}
