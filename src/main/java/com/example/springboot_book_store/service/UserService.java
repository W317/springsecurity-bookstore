package com.example.springboot_book_store.service;

import com.example.springboot_book_store.dto.BookDTO;

public interface UserService {
    void sendRequest(int bookId);

    void cancelRequest(int bookId);
}
