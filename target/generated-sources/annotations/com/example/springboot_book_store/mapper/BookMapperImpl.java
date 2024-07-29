package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.AuthorDTO;
import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.model.Author;
import com.example.springboot_book_store.model.Book;
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
public class BookMapperImpl implements BookMapper {

    @Override
    public Book convertToEntity(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setBookStatus( bookDTO.getBookStatus() );
        book.setAuthors( authorDTOSetToAuthorSet( bookDTO.getAuthors() ) );

        return book;
    }

    @Override
    public BookDTO convertToDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO.BookDTOBuilder bookDTO = BookDTO.builder();

        if ( book.getId() != null ) {
            bookDTO.id( book.getId() );
        }
        bookDTO.title( book.getTitle() );
        bookDTO.bookStatus( book.getBookStatus() );
        bookDTO.authors( authorSetToAuthorDTOSet( book.getAuthors() ) );

        return bookDTO.build();
    }

    protected Author authorDTOToAuthor(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDTO.getId() );
        author.setName( authorDTO.getName() );

        return author;
    }

    protected Set<Author> authorDTOSetToAuthorSet(Set<AuthorDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Author> set1 = new LinkedHashSet<Author>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorDTO authorDTO : set ) {
            set1.add( authorDTOToAuthor( authorDTO ) );
        }

        return set1;
    }

    protected AuthorDTO authorToAuthorDTO(Author author) {
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

    protected Set<AuthorDTO> authorSetToAuthorDTOSet(Set<Author> set) {
        if ( set == null ) {
            return null;
        }

        Set<AuthorDTO> set1 = new LinkedHashSet<AuthorDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Author author : set ) {
            set1.add( authorToAuthorDTO( author ) );
        }

        return set1;
    }
}
