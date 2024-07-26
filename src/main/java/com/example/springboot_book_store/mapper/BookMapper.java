package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.model.Author;
import com.example.springboot_book_store.model.Book;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public BookDTO convertToDTO(Book book) {
        Set<AuthorDTO> authorDTOs = book.getAuthors().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toSet());

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getBookStatus(),
                authorDTOs
        );
    }

    public Book convertToEntity(BookDTO bookDTO) {
        Set<Author> authors = bookDTO.getAuthors().stream()
                .map(author -> new Author(author.getId(), author.getName()))
                .collect(Collectors.toSet());
        return new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getBookStatus(),
                authors
        );
    }
}
