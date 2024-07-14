package com.example.todo_app.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TodoConfig implements CommandLineRunner{

    private final TodoRepository repository;
    
    // @Bean
    // CommandLineRunner commandLineRunner(TodoRepository todoRepository) {
    //     return args -> {
    //         Todo defTodo1 = new Todo(
    //             "Buy milk",
    //             "Buy milk at the store by 5pm",
    //             LocalDate.now(),
    //             Long.valueOf(102)
    //         );

    //         Todo defTodo2 = new Todo(
    //             "Call Mum",
    //             "Call Mum about her funds",
    //             LocalDate.now(),
    //             Long.valueOf(102)
    //         );

    //         todoRepository.saveAll(List.of(defTodo1, defTodo2));
    //     };
    // }

     public TodoConfig(TodoRepository repository) {
        this.repository = repository;
       
    }

    @Override
    public void run(String... args) throws Exception {
     try {
        Todo defTodo1 = new Todo(
            "Buy milk",
            "Buy milk at the store by 5pm",
            LocalDate.now(),
            Long.valueOf(102)
        );

        Todo defTodo2 = new Todo(
            "Call Mum",
            "Call Mum about her fundss",
            LocalDate.now(),
            Long.valueOf(102)
        );

        repository.saveAll(List.of(defTodo1, defTodo2));
       
     } catch (Exception e) {
        // TODO: handle exception
     }
    }

}
