package com.example.springboot_book_store.service;

import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.model.Book;

import java.util.Set;

public interface BookService {
    Set<BookDTO> listAllBooks();

    void addNewBook(BookDTO bookDTO);
}
