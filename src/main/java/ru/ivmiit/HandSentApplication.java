package ru.ivmiit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.ivmiit.models.RefreshToken;
import ru.ivmiit.models.UserEntity;

@SpringBootApplication
public class HandSentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandSentApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:8080/*");
                registry.addMapping("/api/products").allowedOrigins("http://localhost:8080/*");
            }
        };
    }

}
