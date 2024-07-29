package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.model.Author;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T15:29:38+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDTO convertToDTO(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO.AuthorDTOBuilder authorDTO = AuthorDTO.builder();

        if ( author.getId() != null ) {
            authorDTO.id( author.getId() );
        }
        authorDTO.name( author.getName() );

        return authorDTO.build();
    }

    @Override
    public Author convertToEntity(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDTO.getId() );
        author.setName( authorDTO.getName() );

        return author;
    }

    @Override
    public Set<AuthorDTO> convertToDTOs(Set<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        Set<AuthorDTO> set = new LinkedHashSet<AuthorDTO>( Math.max( (int) ( authors.size() / .75f ) + 1, 16 ) );
        for ( Author author : authors ) {
            set.add( convertToDTO( author ) );
        }

        return set;
    }

    @Override
    public Set<Author> convertToEntities(Set<AuthorDTO> authorDTOs) {
        if ( authorDTOs == null ) {
            return null;
        }

        Set<Author> set = new LinkedHashSet<Author>( Math.max( (int) ( authorDTOs.size() / .75f ) + 1, 16 ) );
        for ( AuthorDTO authorDTO : authorDTOs ) {
            set.add( convertToEntity( authorDTO ) );
        }

        return set;
    }
}
