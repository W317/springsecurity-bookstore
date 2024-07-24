package com.example.springboot_book_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Cấu hình cho tất cả các endpoint trong /api
                .allowedOrigins("http://localhost:8081")  // Cho phép yêu cầu từ domain này
                .allowedMethods("GET", "POST", "PUT", "DELETE");  // Cho phép các phương thức này
    }
}
