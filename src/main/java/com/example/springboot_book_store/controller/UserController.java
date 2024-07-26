package com.example.springboot_book_store.controller;

import com.example.springboot_book_store.mapper.UserMapper;
import com.example.springboot_book_store.service.BookService;
import com.example.springboot_book_store.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
public class UserController {
    private BookService bookService;
    private UserMapper userMapper;

    private UserService userService;

    public UserController(BookService bookService, UserMapper userMapper, UserService userService) {
        this.bookService = bookService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<String> requestBorrow(@PathVariable int bookId) {
        userService.sendRequest(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancel/{bookId}")
    public ResponseEntity<String> cancelBorrow(@PathVariable int bookId) {
        userService.cancelRequest(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
