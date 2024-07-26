package com.example.springboot_book_store.service;

import com.example.springboot_book_store.dto.BookDTO;

public interface AdminService {
    void exceptRequest(BookDTO bookDTO);

    void rejectRequest(BookDTO bookDTO);
}
