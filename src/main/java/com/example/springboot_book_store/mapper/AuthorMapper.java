package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.model.Author;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    // Convert Author entity to AuthorDTO
    AuthorDTO convertToDTO(Author author);

    // Convert AuthorDTO to Author entity
    Author convertToEntity(AuthorDTO authorDTO);

    // Convert a set of Author entities to a set of AuthorDTOs
    Set<AuthorDTO> convertToDTOs(Set<Author> authors);

    // Convert a set of AuthorDTOs to a set of Author entities
    Set<Author> convertToEntities(Set<AuthorDTO> authorDTOs);
}
