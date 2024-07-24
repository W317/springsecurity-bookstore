package com.example.springboot_book_store.controller;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.model.Author;
import com.example.springboot_book_store.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

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
