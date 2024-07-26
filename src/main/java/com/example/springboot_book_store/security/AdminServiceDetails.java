//package com.example.springboot_book_store.security;
//
//import com.example.springboot_book_store.model.Admin;
//import com.example.springboot_book_store.repository.AdminRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//public class AdminServiceDetails implements UserDetailsService {
//    private AdminRepository adminRepository;
//
//    public AdminServiceDetails(AdminRepository adminRepository) {
//        this.adminRepository = adminRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin admin = adminRepository.findByUsername(username);
//        if(admin == null){
//            throw new UsernameNotFoundException("Could not find username");
//        }
//        return new User(
//                admin.getUsername(),
//                admin.getPassword(),
//                admin.getRoles()
//                        .stream()
//                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
//    }
//}
