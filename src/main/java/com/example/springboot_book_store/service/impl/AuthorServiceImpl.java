package com.example.springboot_book_store.service.impl;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.exception.GlobalExceptionHandler;
import com.example.springboot_book_store.mapper.AuthorMapper;
import com.example.springboot_book_store.model.Author;
import com.example.springboot_book_store.repository.AuthorRepository;
import com.example.springboot_book_store.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;

    AuthorMapper authorMapper;

    @Override
    public Set<AuthorDTO> listAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::convertToDTO)  // Convert Author entities to AuthorDTO
                .collect(Collectors.toSet());
    }

    @Override
    public void addNewAuthor(AuthorDTO authorDTO) {
        if (!authorRepository.existsAuthorByName(authorDTO.getName())) {
            Author author = authorMapper.convertToEntity(authorDTO);
            authorRepository.save(author);
        } else {
            throw new GlobalExceptionHandler.DuplicateEntityException("Author " + authorDTO.getName() + " is existed");
        }
    }
}
