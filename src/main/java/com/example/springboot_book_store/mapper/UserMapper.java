package com.example.springboot_book_store.mapper;

import com.example.springboot_book_store.dto.UserDTO;
import com.example.springboot_book_store.model.Role;
import com.example.springboot_book_store.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // Convert User entity to UserDTO
    public UserDTO convertToDTO(User user) {
        // Map the roles to a set of strings representing role names
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roles
        );
    }

    // Convert UserDTO to User entity
    public User convertToEntity(UserDTO userDTO) {
        Set<Role> roles = userDTO.getRoles().stream()
                .map(roleName -> new Role(userDTO.getId(), roleName))
                .collect(Collectors.toSet());

        return new User(
                userDTO.getId(),
                userDTO.getUsername(),
                null,
                userDTO.getEmail(),
                roles
        );
    }
}
