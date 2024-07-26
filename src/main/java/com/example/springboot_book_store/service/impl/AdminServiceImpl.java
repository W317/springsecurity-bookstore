package com.example.springboot_book_store.service.impl;

import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.mapper.BookMapper;
import com.example.springboot_book_store.model.Book;
import com.example.springboot_book_store.model.BorrowStatus;
import com.example.springboot_book_store.repository.BookRepository;
import com.example.springboot_book_store.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public AdminServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public void exceptRequest(BookDTO bookDTO) {
        bookDTO.setBookStatus(BorrowStatus.ACCEPTED.getStatus());
        Book book = bookMapper.convertToEntity(bookDTO);
        bookRepository.save(book);
    }

    @Override
    public void rejectRequest(BookDTO bookDTO) {
        bookDTO.setBookStatus(BorrowStatus.REJECTED.getStatus());
        Book book = bookMapper.convertToEntity(bookDTO);
        bookRepository.save(book);
    }
}
