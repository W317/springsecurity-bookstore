package com.example.springboot_book_store.service.impl;

import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.mapper.BookMapper;
import com.example.springboot_book_store.model.Book;
import com.example.springboot_book_store.model.BorrowStatus;
import com.example.springboot_book_store.repository.BookRepository;
import com.example.springboot_book_store.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public UserServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public void sendRequest(int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book Not Found"));
        book.setBookStatus(BorrowStatus.PENDING.getStatus());
        bookRepository.save(book);
    }

    @Override
    public void cancelRequest(int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book Not Found"));
        book.setBookStatus(BorrowStatus.AVAILABLE.getStatus());
        bookRepository.save(book);
    }
}
