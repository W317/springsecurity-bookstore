package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.BookDTO;
import com.example.springboot_book_store.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book convertToEntity(BookDTO bookDTO);

    BookDTO convertToDTO(Book book);

}
