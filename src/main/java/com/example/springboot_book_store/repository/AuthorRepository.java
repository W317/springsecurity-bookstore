package com.example.springboot_book_store.repository;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    boolean existsAuthorByName(String name);

    Optional<Author> findByName(String name);
}
