package com.example.springboot_book_store;

import com.example.springboot_book_store.model.Admin;
import com.example.springboot_book_store.model.Borrower;
import com.example.springboot_book_store.model.Role;
import com.example.springboot_book_store.repository.AdminRepository;
import com.example.springboot_book_store.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class SpringbootBookStoreApplication {
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void createSampleUsers() {
		// Get your password encoder bean
		Role role = new Role(5, "USER");
		User user = new User("user", passwordEncoder.encode("12345"),
				Collections.singletonList(new SimpleGrantedAuthority("USER")));

		Collection<Role> roleCollections = new ArrayList<>();
		roleCollections.add(role);
		Borrower user1 = new Borrower(10, user.getUsername(), user.getPassword(), "", roleCollections );
		userRepository.save(user1);

		Role roleAdmin = new Role(6, "ADMIN");
		User admin = new User("admin", passwordEncoder.encode("12345"),
				Collections.singletonList(new SimpleGrantedAuthority("ADMIN")
		));

		Collection<Role> roleCollections1 = new ArrayList<>();
		roleCollections1.add(roleAdmin);
		Admin admin1 = new Admin(10, admin.getUsername(), admin.getPassword(), "", roleCollections1 );
		adminRepository.save(admin1);
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringbootBookStoreApplication.class, args);
	}

}
