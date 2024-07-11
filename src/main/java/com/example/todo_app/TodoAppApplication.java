package com.example.todo_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Bean
    CommandLineRunner printActiveProfiles(Environment env) {
        return args -> {
            System.out.println("Active profiles: " + String.join(", ", env.getActiveProfiles()));
        };
    }
}
