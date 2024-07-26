package com.example.springboot_book_store.security;

import com.example.springboot_book_store.model.Role;
import com.example.springboot_book_store.model.User;
import com.example.springboot_book_store.model.Admin;
import com.example.springboot_book_store.repository.UserRepository;
import com.example.springboot_book_store.repository.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public CustomUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    mapAuthorities(user.getRoles())
            );
        }

        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(),
                    admin.getPassword(),
                    mapAuthorities(admin.getRoles())
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    private Collection<SimpleGrantedAuthority> mapAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
