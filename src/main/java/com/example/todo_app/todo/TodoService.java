package com.example.todo_app.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // TodoService() {
    //     this.todos = new ArrayList<>();
    //     this.todos.add(todo);
    // }

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    // private Todo todo = new Todo("First Todo", "My Java Spring application", LocalDate.now());
    // private List<Todo> todos = new ArrayList<Todo>();

    

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id).get();
    }

    public void addTodo(Todo todo) {
        Example<Todo> example = Example.of(todo); 
        boolean exists = todoRepository.exists(example);

        if(exists) {
            throw new IllegalStateException("Todo already exists");
        }
        
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        boolean todoExists = todoRepository.existsById(id);

        if(!todoExists) {
            throw new IllegalStateException("Todo with id: " + id + ", does not exist");
        }

        todoRepository.deleteById(id);
    }
    
    @Transactional
    public void updateTodo() {
        
    }
}
