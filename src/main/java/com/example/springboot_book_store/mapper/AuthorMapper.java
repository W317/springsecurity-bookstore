package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.model.Author;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    // Convert Author entity to AuthorDTO
    public AuthorDTO convertToDTO(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getName()
        );
    }

    // Convert AuthorDTO to Author entity
    public Author convertToEntity(AuthorDTO authorDTO) {
        return new Author(
                authorDTO.getId(),
                authorDTO.getName()
        );
    }

    // Convert a set of Author entities to a set of AuthorDTOs
    public Set<AuthorDTO> convertToDTOs(Set<Author> authors) {
        return authors.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    // Convert a set of AuthorDTOs to a set of Author entities
    public Set<Author> convertToEntities(Set<AuthorDTO> authorDTOs) {
        return authorDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet());
    }
}
