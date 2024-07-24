package com.example.springboot_book_store.service;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.model.Author;

import java.util.Set;

public interface AuthorService {
    Set<AuthorDTO> listAllAuthors();

    void addNewAuthor(AuthorDTO authorDTO);
}
