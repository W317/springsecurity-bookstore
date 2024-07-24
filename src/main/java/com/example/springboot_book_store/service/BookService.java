package com.example.springboot_book_store.service;

import com.example.springboot_book_store.model.Book;

import java.util.Set;

public interface BookService {
    Set<Book> listAllBooks();

    void addNewBook(Book book);
}
