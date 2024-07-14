package com.example.todo_app.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(TodoRepository todoRepository) {
        return args -> {
            Todo defTodo1 = new Todo(
                "Buy milk",
                "Buy milk at the store by 5pm",
                LocalDate.now()
            );

            Todo defTodo2 = new Todo(
                "Call Mum",
                "Call Mum about her funds",
                LocalDate.now()
            );

            todoRepository.saveAll(List.of(defTodo1, defTodo2));
        };
    }

}
