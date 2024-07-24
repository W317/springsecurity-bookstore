package com.example.springboot_book_store.service;

import com.example.springboot_book_store.model.Author;

import java.util.Set;

public interface AuthorService {
    Set<Author> listAllAuthors();

    void addNewAuthor(Author author);
}
