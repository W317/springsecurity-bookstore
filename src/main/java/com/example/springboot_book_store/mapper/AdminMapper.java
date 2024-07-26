package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.AdminDTO;
import com.example.springboot_book_store.model.Admin;
import com.example.springboot_book_store.model.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdminMapper {

    // Convert Admin entity to AdminDTO
    public AdminDTO convertToDTO(Admin admin) {
        Set<String> roleNames = admin.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return new AdminDTO(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                roleNames
        );
    }

    // Convert AdminDTO to Admin entity
    public Admin convertToEntity(AdminDTO adminDTO) {
        Set<Role> roles = adminDTO.getRoles().stream()
                .map(roleName -> new Role(adminDTO.getId(), roleName))
                .collect(Collectors.toSet());

        return new Admin(
                adminDTO.getId(),
                adminDTO.getUsername(),
                null,
                adminDTO.getEmail(),
                roles
        );
    }
}
