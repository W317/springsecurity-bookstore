package com.example.springboot_book_store.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {
    int id;
    String title;
    String bookStatus;
    Set<AuthorDTO> authors;
}
