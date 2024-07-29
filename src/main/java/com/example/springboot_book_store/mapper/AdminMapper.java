package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.AdminDTO;
import com.example.springboot_book_store.model.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    // Convert Admin entity to AdminDTO
    AdminDTO convertToDTO(Admin admin);

    // Convert AdminDTO to Admin entity
    Admin convertToEntity(AdminDTO adminDTO);
}
