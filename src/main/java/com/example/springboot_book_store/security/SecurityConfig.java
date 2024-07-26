package com.example.springboot_book_store.security;

import com.example.springboot_book_store.jwt.JwtAuthenticationFilter;
import com.example.springboot_book_store.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/api/authors/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/authors").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/authors/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/authors/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/books/borrow/**").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/api/books/cancel/**").hasRole("USER")

                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
