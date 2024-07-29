package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.UserDTO;
import com.example.springboot_book_store.model.Borrower;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Convert User entity to UserDTO
    UserDTO convertToDTO(Borrower borrower);

    // Convert UserDTO to User entity
    Borrower convertToEntity(UserDTO userDTO);
}
