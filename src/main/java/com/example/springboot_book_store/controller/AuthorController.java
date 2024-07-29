package com.example.springboot_book_store.controller;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.model.Author;
import com.example.springboot_book_store.service.AuthorService;
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
@RequestMapping("/api/authors")
public class AuthorController {
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<Set<AuthorDTO>> showAllAuthors() {
        Set<AuthorDTO> authors = authorService.listAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.addNewAuthor(authorDTO);
        return new ResponseEntity<>("Added Author " + authorDTO.getName() + " Successfully", HttpStatus.CREATED);
    }
}
