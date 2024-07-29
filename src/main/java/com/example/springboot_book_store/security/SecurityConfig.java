package com.example.springboot_book_store.security;

import com.example.springboot_book_store.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
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

                                .requestMatchers("/admin.html").hasRole("ADMIN")
                                .requestMatchers("/user.html").hasRole("USER")
                                .requestMatchers("/css/**", "/js/**", "/login.html").permitAll()

                                .anyRequest().authenticated()
                ).formLogin(formLogin ->
                        formLogin
                                .loginPage("/login.html")
                                .loginProcessingUrl("/api/login")
                                .defaultSuccessUrl("/home.html", true)
                                .permitAll()
                ).logout(LogoutConfigurer::permitAll
                ).exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/home.html"))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}