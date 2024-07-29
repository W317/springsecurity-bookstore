package com.example.springboot_book_store.controller;

import com.example.springboot_book_store.mapper.UserMapper;
import com.example.springboot_book_store.service.BookService;
import com.example.springboot_book_store.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/books")
public class UserController {
    UserService userService;

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
