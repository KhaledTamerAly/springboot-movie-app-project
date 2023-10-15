package com.sumerge.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = {"com.sumerge.Movie", "com.sumerge.Auth"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
