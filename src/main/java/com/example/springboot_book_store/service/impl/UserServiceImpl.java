package com.example.springboot_book_store.service.impl;

import com.example.springboot_book_store.model.Book;
import com.example.springboot_book_store.model.BorrowStatus;
import com.example.springboot_book_store.repository.BookRepository;
import com.example.springboot_book_store.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    BookRepository bookRepository;

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
