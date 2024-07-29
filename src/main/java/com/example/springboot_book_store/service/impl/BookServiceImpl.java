package com.example.springboot_book_store.service.impl;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.exception.GlobalExceptionHandler;
import com.example.springboot_book_store.mapper.AuthorMapper;
import com.example.springboot_book_store.mapper.BookMapper;
import com.example.springboot_book_store.model.Author;
import com.example.springboot_book_store.model.Book;
import com.example.springboot_book_store.model.BorrowStatus;
import com.example.springboot_book_store.repository.AuthorRepository;
import com.example.springboot_book_store.repository.BookRepository;
import com.example.springboot_book_store.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;

    AuthorRepository authorRepository;

    BookMapper bookMapper;

    AuthorMapper authorMapper;

    @Override
    public Set<BookDTO> listAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::convertToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public void addNewBook(BookDTO bookDTO) {
        if (bookRepository.existsBookByTitle(bookDTO.getTitle())) {
            throw new GlobalExceptionHandler.DuplicateEntityException("Book with title " + bookDTO.getTitle() + " already exists.");
        }

        // Check author if duplicated
        Set<Author> authorEntities = new HashSet<>();
        for (AuthorDTO author : bookDTO.getAuthors()) {
            Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
            if (existingAuthor.isPresent()) {
                authorEntities.add(existingAuthor.get());
            } else {
                 // Save the author if it does not exist
                Author newAuthor = authorMapper.convertToEntity(author);
                authorEntities.add(newAuthor);
            }
        }

        // convert to Entity to save in dbs
        bookDTO.setAuthors(authorEntities
                .stream()
                .map(authorMapper::convertToDTO)
                .collect(Collectors.toSet()));
        bookDTO.setBookStatus(BorrowStatus.AVAILABLE.getStatus());
        Book book = bookMapper.convertToEntity(bookDTO);
        bookRepository.save(book);
    }
}
