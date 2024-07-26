package com.example.springboot_book_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // config for all endpoints at /api/...
                .allowedOrigins("http://localhost:8081")  // Allow to receive request from this domain
                .allowedMethods("GET", "POST", "PUT", "DELETE");  // Allow these methods
    }
}
