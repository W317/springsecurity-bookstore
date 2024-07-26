package com.example.springboot_book_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private int id;
    private String username;
    private String email;
    private Set<String> roles;
}
