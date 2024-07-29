package com.example.springboot_book_store.service.impl;

import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.mapper.BookMapper;
import com.example.springboot_book_store.model.Book;
import com.example.springboot_book_store.model.BorrowStatus;
import com.example.springboot_book_store.repository.BookRepository;
import com.example.springboot_book_store.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminServiceImpl implements AdminService {
    BookRepository bookRepository;
    BookMapper bookMapper;

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
