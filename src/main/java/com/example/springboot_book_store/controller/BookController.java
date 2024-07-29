package com.example.springboot_book_store.controller;

import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.model.Book;
import com.example.springboot_book_store.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/books")
public class BookController {
    BookService bookService;

    @GetMapping
    public ResponseEntity<Set<BookDTO>> showAllBooks() {
        Set<BookDTO> books = bookService.listAllBooks();
        return new  ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewBook(@RequestBody BookDTO bookDTO) {
        bookService.addNewBook(bookDTO);
        return new ResponseEntity<>("Added book " + bookDTO.getTitle() + " successfully", HttpStatus.CREATED);
    }


}
