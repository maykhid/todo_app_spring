package com.example.todo_app.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.todo_app.user.User;
import com.example.todo_app.user.UserRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    // TodoService() {
    //     this.todos = new ArrayList<>();
    //     this.todos.add(todo);
    // }

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }
    
    // private Todo todo = new Todo("First Todo", "My Java Spring application", LocalDate.now());
    // private List<Todo> todos = new ArrayList<Todo>();

    

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> getTodosByUser(Authentication authentication) throws Exception{
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        System.err.println("The user used: " + user);
        return todoRepository.findByUserId(user.getUserId());
    }

    public Optional<Todo> getTodo(Long id) {
        return Optional.ofNullable(todoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id: " + id + ", not found")));
    }

    public void addTodo(Todo todo, Authentication authentication) {
        Example<Todo> example = Example.of(todo); 
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        boolean exists = todoRepository.exists(example);

        if(exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Todo already exists");
        }

        todo.setUserId(user.getUserId());
        
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        boolean todoExists = todoRepository.existsById(id);

        if(!todoExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id: " + id + ", does not exist");
        }

        todoRepository.deleteById(id);
    }

    public void updateTodo(Todo newTodo) {
        boolean todoExists = todoRepository.existsById(newTodo.getId());

        if(!todoExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id: " + newTodo.getId() + ", does not exist");
        }

        todoRepository.findById(newTodo.getId()).map(todo -> {
            todo.setTitle(newTodo.getTitle());
            todo.setContent(newTodo.getContent());
            todo.setDate(newTodo.getDate());
            todo.setId(newTodo.getId());
            todo.setUserId(newTodo.getUserId());
            return todoRepository.save(todo);
        });
    }
    
}
